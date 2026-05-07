package org.tobynguyen.solitar.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kotlin.time.Duration.Companion.nanoseconds
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.tobynguyen.solitar.exception.RateLimitExceededException
import org.tobynguyen.solitar.service.RateLimiterService

@Component
class RateLimiterInterceptor(private val rateLimiterService: RateLimiterService) :
    HandlerInterceptor {

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
    ): Boolean {
        return rateLimiterService
            .tryConsumeAndReturnRemaining(request.remoteAddr)
            .also { probe ->
                if (!probe.isConsumed) {
                    throw RateLimitExceededException(
                        probe.nanosToWaitForRefill.nanoseconds.inWholeSeconds
                    )
                }
            }
            .isConsumed
    }
}
