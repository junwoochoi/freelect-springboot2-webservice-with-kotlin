package com.junu.book.web.dto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class HelloResponseDtoTest {

    @ParameterizedTest
    @MethodSource("providerDto")
    fun testConstructor(name: String, amount: Int) {
        //when
        val helloResponseDto = HelloResponseDto(name, amount)

        //then
        assertThat(helloResponseDto).isNotNull
        assertThat(helloResponseDto.name).isEqualTo(name)
        assertThat(helloResponseDto.amount).isEqualTo(amount)
    }
    companion object {
        @JvmStatic
        fun providerDto() = listOf(
                Arguments.of("hi", 2000),
                Arguments.of("hello", 10000),
                Arguments.of("who", 20000),
                Arguments.of("you", 15000)
        )
    }
}