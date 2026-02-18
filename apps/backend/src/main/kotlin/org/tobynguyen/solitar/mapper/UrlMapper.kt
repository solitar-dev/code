package org.tobynguyen.solitar.mapper

import org.tobynguyen.solitar.model.dto.UrlResponseDto
import org.tobynguyen.solitar.model.entity.UrlEntity

fun UrlEntity.toResponseDto(): UrlResponseDto =
    UrlResponseDto(originalUrl = this.originalUrl, shortCode = this.shortCode)
