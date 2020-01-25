package com.junu.book.service.posts

import com.junu.book.domain.posts.PostsRepository
import com.junu.book.web.dto.PostsResponseDto
import com.junu.book.web.dto.PostsSaveRequestDto
import com.junu.book.web.dto.PostsUpdateRequestDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostsService(private val postsRepository: PostsRepository) {
    @Transactional
    fun save(requestDto: PostsSaveRequestDto): Long? = postsRepository.save(requestDto.toEntity()).id

    @Transactional
    fun update(id: Long, postsUpdateRequestDto: PostsUpdateRequestDto): Long? {
        val posts = postsRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("해당 사용자가 없습니다 >> id:${id}")

        posts.update(postsUpdateRequestDto)

        return posts.id
    }

    @Transactional(readOnly = true)
    fun findById(id: Long) : PostsResponseDto {
        val posts = postsRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("해당 사용자가 없습니다 >> id:${id}")
        return PostsResponseDto(posts)
    }
}