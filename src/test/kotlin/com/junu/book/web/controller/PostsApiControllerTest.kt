package com.junu.book.web.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.junu.book.domain.posts.Posts
import com.junu.book.domain.posts.PostsRepository
import com.junu.book.web.dto.PostsSaveRequestDto
import com.junu.book.web.dto.PostsUpdateRequestDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put

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
    @DisplayName("Posts가 등록된다")
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

    @Test
    @DisplayName("Posts가 수정된다")
    fun testUpdate() {
        //given
        val title = "title"
        val postContent = "content"
        val updateTitle = "update title"
        val updateContent = "content updated"
        val savedPost = postsRepository.save(Posts(title = title, content = postContent, author = "author"))

        val requestDto = PostsUpdateRequestDto(title = updateTitle, content = updateContent)

        //when
        val resultActionsDsl = mockMvc.put("/api/v1/posts/${savedPost.id}") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(requestDto)
        }.andDo { print() }
        val posts = postsRepository.findAll()

        //then
        resultActionsDsl.andExpect {
            status { isOk }
            ResultMatcher { it.response.contentAsString.toLongOrNull() == savedPost.id }
        }
        assertThat(posts).hasSize(1)
        assertThat(posts[0].content).isEqualTo(updateContent)
        assertThat(posts[0].title).isEqualTo(updateTitle)
    }

}