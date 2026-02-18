package org.tobynguyen.solitar.repository

import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.tobynguyen.solitar.model.entity.UrlEntity

interface UrlRepository : JpaRepository<UrlEntity, Long> {
    @Cacheable(value = ["urlEntities"], key = "#shortCode", unless = "#result == null")
    fun findByShortCode(shortCode: String): UrlEntity?

    fun findByOriginalUrl(originalUrl: String): List<UrlEntity>

    @Modifying
    @Query("UPDATE UrlEntity u SET u.clickCount = u.clickCount + 1 WHERE u.id = :id")
    fun incrementClickCount(id: Long)

    @Query("SELECT COALESCE(SUM(u.clickCount), 0) FROM UrlEntity u") fun sumAllClicks(): Int
}
