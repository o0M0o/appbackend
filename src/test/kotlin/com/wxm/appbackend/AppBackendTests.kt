package com.wxm.appbackend

import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppBackendTests {
    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    @Throws(Exception::class)
    fun testSayHello() {
        restTemplate.getForObject("/hello", String::class.java).let {
            Assertions.assertThat(it).containsSequence("Hello World!")
        }

        restTemplate.getForObject("/hello?name=ookoo",
                String::class.java).let {
            Assertions.assertThat(it).containsSequence("Hello ookoo!")
        }
    }
}
