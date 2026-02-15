package org.tobynguyen.solitar.repository

import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.tobynguyen.solitar.model.entity.UrlEntity

interface UrlRepository : JpaRepository<UrlEntity, Long> {
    @Cacheable(value = ["shortCodes"], key = "#shortCode")
    fun findByShortCode(shortCode: String): UrlEntity?

    @Modifying
    @Query("UPDATE UrlEntity u SET u.clickCount = u.clickCount + 1 WHERE u.id = :id")
    fun incrementClickCount(id: Long)
}
