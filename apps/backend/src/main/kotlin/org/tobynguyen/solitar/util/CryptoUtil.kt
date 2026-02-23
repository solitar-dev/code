package org.tobynguyen.solitar.util

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder

@Configuration
class CryptoUtil {

    @Bean fun argon2Encoder() = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8()
}
