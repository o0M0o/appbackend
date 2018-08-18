package com.wxm.appbackend

import com.wxm.appbackend.usrmsg.data.UsrMsg
import io.restassured.RestAssured
import junit.framework.Assert.assertEquals
import org.apache.commons.lang3.RandomStringUtils.randomNumeric
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.http.MediaType


@RunWith(SpringRunner::class)
//@SpringBootTest(classes = { AppBackendApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class UsrMsgTests {
    private val API_ROOT = "http://localhost:8080/api/usrMsg"

    @Test
    fun whenGetAllUsrMsg_thenOK() {
        val response = RestAssured.get(API_ROOT)
        assertEquals(HttpStatus.OK.value(), response.statusCode)
    }

    @Test
    fun whenGetCreatedUsrMsgById_thenOK() {
        val um = createRandomUsrMsg()
        val loc = createUsrMsgUri(um)
        val resp = RestAssured.get(loc)
        assertEquals(HttpStatus.OK.value(), resp.statusCode)
        assertEquals(um.usr, resp.jsonPath().get("usr"))

        val resp1 = RestAssured.get(API_ROOT)
        assertEquals(HttpStatus.OK.value(), resp1.statusCode)
    }

    @Test
    fun whenGetNotExistUsrMsgById_thenNotFound() {
        val response = RestAssured.get(API_ROOT + "/" + randomNumeric(4))
        assertEquals(HttpStatus.NOT_FOUND.value(), response.statusCode)
    }

    @Test
    fun whenCreateNewUsrMsg_thenCreated() {
        val response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createRandomUsrMsg())
                .post(API_ROOT)
        assertEquals(HttpStatus.CREATED.value(), response.statusCode)
    }

    /*
    @Test
    fun whenInvalidUsrMsg_thenError() {
        val response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createRandomUsrMsg())
                .post(API_ROOT)
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode)
    }
    */


    @Test
    fun whenUpdateCreatedBook_thenUpdated() {
        val um = createRandomUsrMsg()
        val loc = createUsrMsgUri(um)
        val nmsg = getRandomMsg()

        um.id = (loc.split("api/usrMsg/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]).toInt()
        um.msg = nmsg
        var response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(um)
                .put(loc)

        assertEquals(HttpStatus.OK.value(), response.statusCode)

        response = RestAssured.get(loc)

        assertEquals(HttpStatus.OK.value(), response.statusCode)
        assertEquals(nmsg, response.jsonPath().get("msg"))
    }

    @Test
    fun whenDeleteCreatedUsrMsg_thenOk() {
        val um = createRandomUsrMsg()
        val loc = createUsrMsgUri(um)
        var response = RestAssured.delete(loc)

        assertEquals(HttpStatus.OK.value(), response.statusCode)

        response = RestAssured.get(loc)
        assertEquals(HttpStatus.NOT_FOUND.value(), response.statusCode)
    }


    /// PRIVATE BEGIN
    private fun getRandomUsr(): String {
        return TestUtil.getRandomStr(5)
    }

    private fun getRandomMsg(): String {
        return TestUtil.getRandomStr(TestUtil.getRandomNum(10, 30))
    }

    private fun getRandomReplyAddress(): String {
        return TestUtil.getRandomStr(TestUtil.getRandomNum(5, 8)) + "@" + TestUtil.getRandomStr(3) + ".com"
    }

    private fun createRandomUsrMsg(): UsrMsg {
        return UsrMsg().apply {
            usr = getRandomUsr()
            msg = getRandomMsg()
            replyAddress = getRandomReplyAddress()
        }
    }

    private fun createUsrMsgUri(um: UsrMsg): String {
        val response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(um)
                .post(API_ROOT)
        return API_ROOT + "/" + response.jsonPath().get("id")
    }
    /// PRIVATE END
}