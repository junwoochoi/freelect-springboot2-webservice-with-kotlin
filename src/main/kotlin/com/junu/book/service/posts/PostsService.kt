package com.junu.book.service.posts

import com.junu.book.domain.posts.PostsRepository
import com.junu.book.web.dto.PostsSaveRequestDto
import org.springframework.stereotype.Service

@Service
class PostsService(private val postsRepository: PostsRepository) {
    fun save(requestDto: PostsSaveRequestDto): Long? = postsRepository.save(requestDto.toEntity()).id
}