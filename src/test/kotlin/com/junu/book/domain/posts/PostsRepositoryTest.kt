package com.junu.book.domain.posts

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
internal class PostsRepositoryTest(private val postsRepository: PostsRepository) {

    @BeforeEach
    fun cleanUp() = postsRepository.deleteAll()

    @Test
    @DisplayName("게시글 저장, 불러오기 테스트")
    fun testSaveAndFind() {
        //given
        val title = "게시글 제목"
        val content = "게시글 본문"
        val author = "junwoochoi@github.com"
        postsRepository.save(Posts(title = title, content = content, author = author))

        //when
        val posts = postsRepository.findAll()

        //then
        assertThat(posts).hasSize(1)
        val post = posts[0]
        assertThat(post).isNotNull
        assertThat(post.title).isEqualTo(title)
        assertThat(post.content).isEqualTo(content)
        assertThat(post.author).isEqualTo(author)
    }

    @Test
    @DisplayName("BaseTimeEntity 적용")
    fun testBaseTimeEntity() {
        //given
        val now = LocalDateTime.now()

        val title = "게시글 제목"
        val content = "게시글 본문"
        val author = "junwoochoi@github.com"

        //when
        val savedPost = postsRepository.save(Posts(title = title, content = content, author = author))

        //then
        assertThat(savedPost.createdDate).isAfter(now)
        assertThat(savedPost.modifiedDate).isAfter(now)
    }
}