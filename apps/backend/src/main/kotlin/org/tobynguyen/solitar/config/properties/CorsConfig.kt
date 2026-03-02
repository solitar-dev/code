package org.tobynguyen.solitar.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@ConfigurationProperties(prefix = "app")
data class AppCorsConfig(val cors: CorsConfig) {
    data class CorsConfig(val allowedOrigins: List<String> = emptyList())
}

@Configuration
class CorsConfig(private val appCorsConfig: AppCorsConfig) : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        val origins = appCorsConfig.cors.allowedOrigins.toTypedArray()

        registry.addMapping("/**").allowedOrigins(*origins).allowedMethods("GET", "POST")
    }
}
