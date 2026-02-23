package org.tobynguyen.solitar.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CryptoUtilTests {

    private val cryptoUtil = CryptoUtil()

    @Test
    fun `argon2Encoder should successfully encode and match passwords`() {
        val encoder = cryptoUtil.argon2Encoder()
        val rawPassword = "mySecurePassword"

        val encodedPassword = encoder.encode(rawPassword)
        val matches = encoder.matches(rawPassword, encodedPassword)
        val wrongMatches = encoder.matches("wrongPassword", encodedPassword)

        assertThat(encodedPassword).isNotEqualTo(rawPassword)
        assertThat(matches).isTrue()
        assertThat(wrongMatches).isFalse()
    }
}
