package org.tobynguyen.solitar.service

import java.time.Instant
import org.springframework.dao.DataIntegrityViolationException
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

        if (data.alias == null) {
            val existing =
                urlRepository.findByOriginalUrl(data.url).firstOrNull {
                    (it.expiresAt == null || it.expiresAt!!.isAfter(Instant.now())) && !it.hasAlias
                }

            if (existing != null) {
                if (existing.isDisabled) {
                    throw UrlDisabledException("We cannot shorten this URL")
                }

                return existing
            }

            val entity =
                UrlEntity(shortCode = "", originalUrl = data.url, expiresAt = data.expiresTime)

            val savedEntity = urlRepository.save(entity)

            val generatedCode = sqids.encode(listOf(savedEntity.id))

            savedEntity.shortCode = generatedCode

            return savedEntity
        } else {
            val entity =
                UrlEntity(
                    shortCode = data.alias,
                    originalUrl = data.url,
                    expiresAt = data.expiresTime,
                    hasAlias = true,
                )

            try {
                return urlRepository.saveAndFlush(entity)
            } catch (e: DataIntegrityViolationException) {
                throw UrlShortCodeConflictedException("This alias already exists.")
            }
        }
    }
}
