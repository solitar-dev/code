package org.tobynguyen.solitar.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.tobynguyen.solitar.config.properties.AppConfig
import org.tobynguyen.solitar.interceptor.RateLimiterInterceptor

@Configuration
class WebConfig(
    private val appConfig: AppConfig,
    private val rateLimiterInterceptor: RateLimiterInterceptor,
) : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        val origins = appConfig.cors.allowedOrigins.toTypedArray()

        registry.addMapping("/**").allowedOrigins(*origins).allowedMethods("GET", "POST")
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(rateLimiterInterceptor).addPathPatterns("/create/**")
    }
}
