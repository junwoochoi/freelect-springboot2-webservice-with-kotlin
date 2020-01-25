package com.junu.book.web.controller

import com.junu.book.service.posts.PostsService
import com.junu.book.web.dto.PostsSaveRequestDto
import com.junu.book.web.dto.PostsUpdateRequestDto
import org.springframework.web.bind.annotation.*

@RestController
class PostsApiController(private val postsService: PostsService) {

    @PostMapping("/api/v1/posts")
    fun save(@RequestBody postsSaveRequestDto: PostsSaveRequestDto): Long? = postsService.save(postsSaveRequestDto)

    @GetMapping("/api/v1/posts/{id}")
    fun findById(@PathVariable id: Long) = postsService.findById(id)

    @PutMapping("/api/v1/posts/{id}")
    fun update(@PathVariable id: Long, @RequestBody postsUpdateRequestDto: PostsUpdateRequestDto) =
            postsService.update(id, postsUpdateRequestDto)
    @DeleteMapping("/api/v1/posts/{id}")
    fun delete(@PathVariable id: Long) = postsService.delete(id)

}
