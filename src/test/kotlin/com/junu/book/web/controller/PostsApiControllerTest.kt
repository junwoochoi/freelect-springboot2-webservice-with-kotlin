package com.junu.book.web.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.junu.book.domain.posts.PostsRepository
import com.junu.book.web.dto.PostsSaveRequestDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.post

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AutoConfigureMockMvc
internal class PostsApiControllerTest(
        private val mockMvc: MockMvc,
        private val objectMapper : ObjectMapper,
        private val postsRepository: PostsRepository
        ) {

    @AfterEach
    fun cleanup() = postsRepository.deleteAll()

    @Test
    fun testCreate() {
        //given
        val title = "title"
        val postContent = "content"

        val requestDto = PostsSaveRequestDto(title = title, content = postContent, author = "author")

        //when
        val resultActionsDsl = mockMvc.post("/api/v1/posts") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(requestDto)
        }.andDo { print() }
        val posts = postsRepository.findAll()

        //then
        resultActionsDsl.andExpect {
            status { isOk }
            ResultMatcher { it.response.contentAsString.toLong() > 0 }
        }
        assertThat(posts).hasSize(1)
        assertThat(posts[0].content).isEqualTo(postContent)
        assertThat(posts[0].title).isEqualTo(title)
    }

}