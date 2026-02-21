package org.tobynguyen.solitar.util

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.sqids.Sqids

@Configuration
class SqidsUtil {

    @Bean
    fun sqids(): Sqids {
        return Sqids(minLength = 7)
    }
}
