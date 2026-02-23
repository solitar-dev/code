package org.tobynguyen.solitar.service

import java.time.Instant
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.sqids.Sqids
import org.tobynguyen.solitar.exception.UrlDisabledException
import org.tobynguyen.solitar.exception.UrlExpiredException
import org.tobynguyen.solitar.exception.UrlNotFoundException
import org.tobynguyen.solitar.exception.UrlProtectedException
import org.tobynguyen.solitar.exception.UrlShortCodeConflictedException
import org.tobynguyen.solitar.mapper.toResponseDto
import org.tobynguyen.solitar.model.dto.UrlCreateDto
import org.tobynguyen.solitar.model.dto.UrlForwardDto
import org.tobynguyen.solitar.model.dto.UrlForwardResponseDto
import org.tobynguyen.solitar.model.entity.UrlEntity
import org.tobynguyen.solitar.repository.UrlRepository

@Service
class UrlService(
    private val urlRepository: UrlRepository,
    private val sqids: Sqids,
    private val argon2Encoder: Argon2PasswordEncoder,
) {

    @Transactional
    fun getOriginalUrl(data: UrlForwardDto): UrlForwardResponseDto {
        val (shortCode, password) = data

        val urlEntity =
            urlRepository.findByShortCode(shortCode)
                ?: throw UrlNotFoundException("Short URL '$shortCode' does not exist.")

        if (urlEntity.isDisabled)
            throw UrlDisabledException("This URL has been disabled due to violation of terms.")

        if (urlEntity.expiresAt != null && urlEntity.expiresAt!!.isBefore(Instant.now()))
            throw UrlExpiredException("This URL is no longer available")

        urlRepository.incrementClickCount(urlEntity.id)

        return if (urlEntity.password == null) {
            UrlForwardResponseDto(urlEntity.toResponseDto().originalUrl)
        } else {
            if (password == null)
                throw UrlProtectedException("Please provide a valid password to unlock this URL.")

            if (argon2Encoder.matches(password, urlEntity.password)) {
                UrlForwardResponseDto(urlEntity.toResponseDto().originalUrl)
            } else {
                throw UrlDisabledException("Incorrect password")
            }
        }
    }

    @Transactional
    fun createUrl(data: UrlCreateDto): UrlEntity {
        val (url, expireTime, alias, password) = data

        val hashedPassword =
            if (password != null) {
                argon2Encoder.encode(password)
            } else {
                null
            }

        if (alias != null) {
            val existing = urlRepository.findByShortCode(alias)

            return if (existing != null) {
                if (
                    existing.originalUrl == url &&
                        existing.expiresAt == expireTime &&
                        existing.password == null
                ) {
                    existing
                } else {
                    throw UrlShortCodeConflictedException("This alias already exists.")
                }
            } else {
                createAndSaveUrl(url, alias, expireTime, hashedPassword)
            }
        } else {
            if (expireTime == null) {
                val existing =
                    urlRepository.findByOriginalUrlAndExpiresAtIsNullAndHasAliasFalse(url)

                if (existing.isEmpty()) {
                    return createAndSaveUrl(url, alias, expireTime, hashedPassword)
                }

                existing.forEach {
                    if (
                        (password == null && it.password == null) ||
                            argon2Encoder.matches(password, it.password)
                    )
                        return it
                }

                return createAndSaveUrl(url, null, null, hashedPassword)
            } else {
                val existing =
                    urlRepository.findByOriginalUrlAndExpiresAtAndHasAliasFalse(url, expireTime)

                if (existing.isEmpty()) {
                    return createAndSaveUrl(url, alias, expireTime, hashedPassword)
                }

                existing.forEach {
                    if (
                        (password == null && it.password == null) ||
                            argon2Encoder.matches(password, it.password)
                    )
                        return it
                }

                return createAndSaveUrl(url, null, expireTime, hashedPassword)
            }
        }
    }

    fun createAndSaveUrl(
        url: String,
        alias: String?,
        expireTime: Instant?,
        hashedPassword: String?,
    ): UrlEntity {
        if (alias != null) {
            val entity =
                UrlEntity(
                    shortCode = alias,
                    originalUrl = url,
                    expiresAt = expireTime,
                    hasAlias = true,
                    password = hashedPassword,
                )

            return urlRepository.saveAndFlush(entity)
        } else {
            val entity =
                UrlEntity(
                    shortCode = "",
                    originalUrl = url,
                    expiresAt = expireTime,
                    password = hashedPassword,
                )

            val savedEntity = urlRepository.save(entity)

            val generatedCode = sqids.encode(listOf(savedEntity.id))

            savedEntity.shortCode = generatedCode

            return savedEntity
        }
    }
}
