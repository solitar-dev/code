package org.tobynguyen.solitar.config

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.redis.connection.RedisConnectionFactory

@Configuration
@Profile("dev")
class RedisDevConfig {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(RedisDevConfig::class.java)
    }

    @Bean
    fun clearRedisCache(connectionFactory: RedisConnectionFactory): CommandLineRunner {
        return CommandLineRunner {
            try {
                connectionFactory.connection.serverCommands().flushDb()
                LOGGER.info("Redis got flushed successfully")
            } catch (e: Exception) {
                LOGGER.error("Failed to clear Redis: ${e.message}")
            }
        }
    }
}
