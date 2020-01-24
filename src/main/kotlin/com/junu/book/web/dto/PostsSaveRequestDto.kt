package com.junu.book.web.dto

import com.junu.book.domain.posts.Posts

data class PostsSaveRequestDto(
        val title: String,
        val author: String,
        val content: String
) {
    fun toEntity(): Posts = Posts(title = title, author = author, content = content)
}
