package com.junu.book.config.auth

import java.lang.annotation.ElementType

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class LoginUser {
}