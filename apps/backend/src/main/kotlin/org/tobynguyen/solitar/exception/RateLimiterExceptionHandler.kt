package org.tobynguyen.solitar.exception

import io.github.wimdeblauwe.errorhandlingspringbootstarter.ApiErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RateLimiterExceptionHandler {
    @ExceptionHandler(RateLimitExceededException::class)
    fun handleRateLimitExceeded(
        exception: RateLimitExceededException
    ): ResponseEntity<ApiErrorResponse> =
        ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
            .header("Retry-After", exception.retryAfterSeconds.toString())
            .body(
                ApiErrorResponse(
                    HttpStatus.TOO_MANY_REQUESTS,
                    "TOO_MANY_REQUESTS",
                    exception.message,
                )
            )
}
