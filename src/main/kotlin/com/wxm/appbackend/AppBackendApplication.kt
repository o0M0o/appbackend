package com.wxm.appbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@EnableJpaRepositories("com.wxm.appbackend.usrmsg.data",
        "com.wxm.appbackend.status")
@EntityScan("com.wxm.appbackend.usrmsg.data",
        "com.wxm.appbackend.usrmsg.status")
@SpringBootApplication
class AppBackendApplication

fun main(args: Array<String>) {
    runApplication<AppBackendApplication>(*args)
}
