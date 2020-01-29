package com.junu.book.config.auth

import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.servlet.http.HttpSession

@Component
class LoginUserArgumentResolver(private val httpSession: HttpSession) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        val isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser::class.java) != null
        val isUserClass = SessionUser::class.java == parameter.parameterType
        return isLoginUserAnnotation && isUserClass
    }

    override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?): Any? {
        return httpSession.getAttribute("user")
    }


}