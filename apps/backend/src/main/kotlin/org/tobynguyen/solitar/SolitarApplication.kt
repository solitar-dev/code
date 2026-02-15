package org.tobynguyen.solitar

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
@RestController
class SolitarApplication {

    @field:Value($$"${SITE_URL}") private lateinit var siteUrl: String

    @Bean
    fun corsConfigurer() =
        object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**").allowedOrigins(siteUrl).allowedMethods("GET", "POST")
            }
        }

    @GetMapping fun index(): String = "Hello World"
}

fun main(args: Array<String>) {
    runApplication<SolitarApplication>(*args)
}
