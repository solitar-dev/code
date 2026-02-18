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
import org.tobynguyen.solitar.model.dto.StatisticDto
import org.tobynguyen.solitar.model.entity.UrlEntity

@Configuration
@EnableCaching
class RedisConfig {
    @Bean
    fun cacheManager(connectionFactory: RedisConnectionFactory): CacheManager {
        val urlEntitySerializer = JacksonJsonRedisSerializer(UrlEntity::class.java)
        val statisticDtoSerializer = JacksonJsonRedisSerializer(StatisticDto::class.java)

        val urlEntityConfig =
            RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(48))
                .disableCachingNullValues()
                .serializeValuesWith(
                    RedisSerializationContext.SerializationPair.fromSerializer(urlEntitySerializer)
                )
        val statisticConfig =
            RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(30))
                .disableCachingNullValues()
                .serializeValuesWith(
                    RedisSerializationContext.SerializationPair.fromSerializer(
                        statisticDtoSerializer
                    )
                )

        val config = mapOf("urlEntities" to urlEntityConfig, "statistics" to statisticConfig)

        return RedisCacheManager.builder(connectionFactory)
            .withInitialCacheConfigurations(config)
            .build()
    }
}
