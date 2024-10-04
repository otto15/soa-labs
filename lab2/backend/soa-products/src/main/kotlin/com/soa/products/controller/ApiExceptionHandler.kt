package com.soa.products.controller

import com.soa.products.exception.PersonNotFoundException
import com.soa.products.exception.ProductNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.Instant

@ControllerAdvice
class ApiExceptionHandler {
    @ExceptionHandler(value = [
        ProductNotFoundException::class,
        PersonNotFoundException::class,
    ])
    fun handleNotFoundException(e: Exception): ResponseEntity<ApiErrorTo> =
        ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ApiErrorTo(e.message))
}

data class ApiErrorTo(
    val message: String?,
    val timestamp: Instant = Instant.now()
)
