package org.tobynguyen.solitar.config

import java.time.Duration
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.tobynguyen.solitar.model.entity.UrlEntity

@Configuration
@EnableCaching
class RedisConfig {
    @Bean
    fun cacheManager(connectionFactory: RedisConnectionFactory): CacheManager {
        val serializer = JacksonJsonRedisSerializer(UrlEntity::class.java)

        val defaults =
            RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(48))
                .disableCachingNullValues()
                .serializeValuesWith(
                    RedisSerializationContext.SerializationPair.fromSerializer(serializer)
                )

        return RedisCacheManager.builder(connectionFactory).cacheDefaults(defaults).build()
    }
}
