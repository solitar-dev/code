package org.tobynguyen.solitar.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.sqids.Sqids

@Configuration
class AppConfig {

    @Bean
    fun sqids(): Sqids {
        return Sqids(minLength = 7)
    }
}
