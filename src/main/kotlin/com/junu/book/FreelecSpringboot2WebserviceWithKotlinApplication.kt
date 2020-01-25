package com.junu.book

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class FreelecSpringboot2WebserviceWithKotlinApplication

fun main(args: Array<String>) {
    runApplication<FreelecSpringboot2WebserviceWithKotlinApplication>(*args)
}
