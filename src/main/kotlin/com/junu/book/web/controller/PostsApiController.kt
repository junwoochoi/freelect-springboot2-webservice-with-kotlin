package com.junu.book.web.controller

import com.junu.book.service.posts.PostsService
import com.junu.book.web.dto.PostsSaveRequestDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostsApiController(private val postsService: PostsService) {

    @PostMapping("/api/v1/posts")
    fun save(@RequestBody postsSaveRequestDto: PostsSaveRequestDto): Long? = postsService.save(postsSaveRequestDto)
}
