package org.tobynguyen.solitar.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app")
data class AppConfig(val cors: CorsConfig) {
    data class CorsConfig(val allowedOrigins: List<String> = emptyList())
}
