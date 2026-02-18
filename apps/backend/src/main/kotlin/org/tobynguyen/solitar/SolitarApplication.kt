package org.tobynguyen.solitar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.tobynguyen.solitar.config.AppCorsConfig

@SpringBootApplication
@EnableConfigurationProperties(AppCorsConfig::class)
@RestController
class SolitarApplication {
    @GetMapping fun index(): String = "Hello World"
}

fun main(args: Array<String>) {
    runApplication<SolitarApplication>(*args)
}
