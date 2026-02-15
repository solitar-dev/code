package org.tobynguyen.solitar.model.dto

import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.time.Instant

data class UrlCreateDto(
    @field:NotBlank(message = "URL is required")
    @field:Pattern(
        message = "Invalid URL",
        regexp =
            "^https?://(?:www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b[-a-zA-Z0-9()@:%_+.~#?&/=]*$",
    )
    val url: String,
    @field:Future(message = "Expiration time must be in the future") val expiresTime: Instant?,
    @field:Size(message = "Alias must be longer than 7 characters", min = 7)
    @field:Size(message = "Alias cannot exceed 255 characters", max = 255)
    @field:Pattern(
        message = "Alias can only contain letters, numbers, dashes, and underscores",
        regexp = "^[a-zA-Z0-9_-]*$",
    )
    val alias: String?,
) {}

data class UrlResponseDto(val originalUrl: String, val shortCode: String) {}

data class UrlForwardResponseDto(val originalUrl: String) {}
