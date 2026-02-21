package org.tobynguyen.solitar.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SqidsUtilTests {
    private val sqidsUtil = SqidsUtil()

    @Test
    fun `sqids should be configured with minimum length of 7`() {
        val sqids = sqidsUtil.sqids()
        val inputNumbers = listOf(1L)

        val encoded = sqids.encode(inputNumbers)
        val decoded = sqids.decode(encoded)

        assertThat(encoded.length).isGreaterThanOrEqualTo(7)
        assertThat(decoded).isEqualTo(inputNumbers)
    }
}
