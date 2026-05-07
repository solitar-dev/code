package org.tobynguyen.solitar.config

import io.github.bucket4j.BucketConfiguration
import io.github.bucket4j.redis.lettuce.Bucket4jLettuce
import io.github.bucket4j.redis.lettuce.cas.LettuceBasedProxyManager
import io.lettuce.core.RedisClient
import io.lettuce.core.codec.ByteArrayCodec
import io.lettuce.core.codec.RedisCodec
import io.lettuce.core.codec.StringCodec
import java.time.Duration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.tobynguyen.solitar.config.properties.AppConfig

@Configuration
class RateLimiterConfig(private val appConfig: AppConfig) {

    @Bean
    fun lettuceBasedProxyManager(
        connectionFactory: LettuceConnectionFactory
    ): LettuceBasedProxyManager<String> {
        val client = connectionFactory.nativeClient as RedisClient

        // key string | value byte[]
        val codec = RedisCodec.of(StringCodec.UTF8, ByteArrayCodec.INSTANCE)
        val connection = client.connect(codec)
        val proxyManager = Bucket4jLettuce.casBasedBuilder(connection).build()

        return proxyManager
    }

    @Bean
    fun bucketConfiguration(): BucketConfiguration {
        return BucketConfiguration.builder()
            .addLimit {
                it.capacity(appConfig.rateLimiter.capacity)
                    .refillIntervally(
                        appConfig.rateLimiter.capacity,
                        Duration.ofSeconds(appConfig.rateLimiter.refillPeriodInSeconds),
                    )
            }
            .build()
    }
}
