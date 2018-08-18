package com.wxm.appbackend.utility

import com.wxm.appbackend.usrmsg.data.UsrMsgIdMismatchExeption
import com.wxm.appbackend.usrmsg.data.UsrMsgNotFoundException
import org.hibernate.exception.ConstraintViolationException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.http.HttpStatus
import org.springframework.http.HttpHeaders
import org.springframework.web.context.request.WebRequest
import org.springframework.http.ResponseEntity
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(UsrMsgNotFoundException::class)
    protected fun handleNotFound(ex: Exception, request: WebRequest): ResponseEntity<Any> {
        return handleExceptionInternal(ex, "Book not found",
                HttpHeaders(), HttpStatus.NOT_FOUND, request)
    }

    @ExceptionHandler(UsrMsgIdMismatchExeption::class,
            ConstraintViolationException::class, DataIntegrityViolationException::class)
    fun handleBadRequest(ex: Exception, request: WebRequest): ResponseEntity<Any> {
        return handleExceptionInternal(ex, ex.localizedMessage,
                HttpHeaders(), HttpStatus.BAD_REQUEST, request)
    }
}