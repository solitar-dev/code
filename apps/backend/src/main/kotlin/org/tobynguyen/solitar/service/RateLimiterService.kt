package org.tobynguyen.solitar.service

import io.github.bucket4j.BucketConfiguration
import io.github.bucket4j.ConsumptionProbe
import io.github.bucket4j.redis.lettuce.cas.LettuceBasedProxyManager
import org.springframework.stereotype.Service

@Service
class RateLimiterService(
    private val proxyManager: LettuceBasedProxyManager<String>,
    private val bucketConfiguration: BucketConfiguration,
) {
    fun tryConsumeAndReturnRemaining(key: String): ConsumptionProbe {
        val bucket = proxyManager.getProxy(key, ::bucketConfiguration)

        return bucket.tryConsumeAndReturnRemaining(1)
    }
}
