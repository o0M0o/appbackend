package com.wxm.appbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AppBackendApplication

fun main(args: Array<String>) {
    runApplication<AppBackendApplication>(*args)
}
