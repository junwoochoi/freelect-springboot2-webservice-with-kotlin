package com.junu.book.config.auth

import com.junu.book.domain.user.User
import java.io.Serializable

data class SessionUser(val name: String, val email: String, val picture: String) : Serializable {
    constructor(user: User) : this(user.name, user.email, user.picture)
}