package org.tobynguyen.solitar.config.properties

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfig(private val appConfig: AppConfig) : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        val origins = appConfig.cors.allowedOrigins.toTypedArray()

        registry.addMapping("/**").allowedOrigins(*origins).allowedMethods("GET", "POST")
    }
}
