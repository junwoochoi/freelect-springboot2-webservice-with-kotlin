package com.junu.book.config.auth

import com.junu.book.domain.user.Role
import com.junu.book.domain.user.User

class OAuthAttributes private constructor(
        val attributes: Map<String, Any>,
        val nameAttributeKey: String,
        val name: String,
        val email: String,
        val picture: String
) {
    fun toEntity(): User = User(name = this.name, email = this.email, picture = this.picture, role = Role.USER)

    companion object {
        fun of(registrationId: String, userNameAttributeName: String, attributes: Map<String, Any>): OAuthAttributes {
            return ofGoogle(userNameAttributeName, attributes)
        }

        fun ofGoogle(userNameAttributeName: String, attributes: Map<String, Any>): OAuthAttributes {
            return OAuthAttributes(
                    name = attributes["name"] as String,
                    email = attributes["email"] as String,
                    picture = attributes["picture"] as String,
                    attributes = attributes,
                    nameAttributeKey = userNameAttributeName
            )
        }
    }

}
