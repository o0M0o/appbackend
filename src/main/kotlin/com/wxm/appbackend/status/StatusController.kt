package com.wxm.appbackend.status

import java.util.concurrent.atomic.AtomicLong
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * for backend status
 */
@RestController
class StatusController {
    val counter = AtomicLong()

    /**
     * reply [name] in param
     */
    @GetMapping("/hello")
    fun sayHello(@RequestParam(value = "name", defaultValue = "World") name: String) =
            Greeting(counter.incrementAndGet(), "Hello $name!")
}
