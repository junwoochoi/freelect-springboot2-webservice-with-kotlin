package com.junu.book.web.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.get

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AutoConfigureMockMvc
internal class IndexControllerTest(private val mockMvc: MockMvc) {

    @Test
    @DisplayName("메인페이지 로딩")
    fun testMainPage() {
        //when & then
        mockMvc.get("/")
                .andDo { print() }
                .andExpect {
                    status { isOk }
                    ResultMatcher { it.response.contentAsString.contains("스프링 부트로 시작하는 웹 서비스") }
                }
    }
}