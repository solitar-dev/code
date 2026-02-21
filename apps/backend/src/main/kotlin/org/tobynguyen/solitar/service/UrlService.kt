package org.tobynguyen.solitar.service

import java.time.Instant
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.sqids.Sqids
import org.tobynguyen.solitar.exception.UrlDisabledException
import org.tobynguyen.solitar.exception.UrlExpiredException
import org.tobynguyen.solitar.exception.UrlNotFoundException
import org.tobynguyen.solitar.exception.UrlShortCodeConflictedException
import org.tobynguyen.solitar.mapper.toResponseDto
import org.tobynguyen.solitar.model.dto.UrlCreateDto
import org.tobynguyen.solitar.model.entity.UrlEntity
import org.tobynguyen.solitar.repository.UrlRepository

@Service
class UrlService(private val urlRepository: UrlRepository, private val sqids: Sqids) {

    @Transactional
    fun getOriginalUrl(shortCode: String): String {
        val urlEntity =
            urlRepository.findByShortCode(shortCode)
                ?: throw UrlNotFoundException("Short URL '$shortCode' does not exist.")

        if (urlEntity.isDisabled)
            throw UrlDisabledException("This URL has been disabled due to violation of terms.")

        if (urlEntity.expiresAt != null && urlEntity.expiresAt!!.isBefore(Instant.now()))
            throw UrlExpiredException("This URL is no longer available")

        urlRepository.incrementClickCount(urlEntity.id)

        return urlEntity.toResponseDto().originalUrl
    }

    @Transactional
    fun createUrl(data: UrlCreateDto): UrlEntity {
        val (url, expireTime, alias) = data

        if (alias != null) {
            val existing = urlRepository.findByShortCode(alias)

            return if (existing != null) {
                if (existing.originalUrl == url && existing.expiresAt == expireTime) {
                    existing
                } else {
                    throw UrlShortCodeConflictedException("This alias already exists.")
                }
            } else {
                createAndSaveUrl(url, alias, expireTime)
            }
        } else {
            if (expireTime == null) {
                val existing =
                    urlRepository.findByOriginalUrlAndExpiresAtIsNullAndHasAliasFalse(url)

                return existing ?: createAndSaveUrl(url, null, null)
            } else {
                val existing =
                    urlRepository.findByOriginalUrlAndExpiresAtAndHasAliasFalse(url, expireTime)

                return existing ?: createAndSaveUrl(url, null, expireTime)
            }
        }
    }

    fun createAndSaveUrl(url: String, alias: String?, expireTime: Instant?): UrlEntity {
        if (alias != null) {
            val entity =
                UrlEntity(
                    shortCode = alias,
                    originalUrl = url,
                    expiresAt = expireTime,
                    hasAlias = true,
                )

            return urlRepository.saveAndFlush(entity)
        } else {
            val entity = UrlEntity(shortCode = "", originalUrl = url, expiresAt = expireTime)

            val savedEntity = urlRepository.save(entity)

            val generatedCode = sqids.encode(listOf(savedEntity.id))

            savedEntity.shortCode = generatedCode

            return savedEntity
        }
    }
}
