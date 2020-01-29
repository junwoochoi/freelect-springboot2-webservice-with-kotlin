package com.junu.book.config.auth

import com.junu.book.domain.user.Role
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class SecurityConfig(private val customOAuth2UserService: CustomOAuth2UserService) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {
        http?.let { httpSecurity ->
            httpSecurity
                    .csrf().disable()
                    .headers().frameOptions().disable()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name)
                    .anyRequest().authenticated()
                    .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .and()
                    .oauth2Login()
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService)
        }
    }
}