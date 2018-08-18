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


    @GetMapping("/status")
    fun getStatus(@RequestParam(value = "name", defaultValue = "World") name: String) =
            Status(counter.incrementAndGet(), 1.0, 1.0)


    /**
     * reply [name] in param
     * example :
     *      /hello  --> hello World!
     *      /hello?name=ookoo  --> hello ookoo!
     */
    @GetMapping("/hello")
    fun sayHello(@RequestParam(value = "name", defaultValue = "World") name: String) =
            Hello(counter.incrementAndGet(), "Hello $name!")
}
