package com.soa.products.controller

import com.soa.products.exception.ProductWithMinPartNumberNotFound
import com.soa.products.exception.PersonDuplicationException
import com.soa.products.exception.PersonNotFoundException
import com.soa.products.exception.ProductDuplicationException
import com.soa.products.exception.ProductNotFoundException
import generated.soa.products.dto.ApiErrorTo
import generated.soa.products.dto.CreatePerson409ErrorTo
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.Instant

@ControllerAdvice
class ApiExceptionHandler {
    @ExceptionHandler(
        value = [
            ProductNotFoundException::class,
            PersonNotFoundException::class,
            ProductWithMinPartNumberNotFound::class,
        ]
    )
    fun handleNotFoundException(e: Exception): ResponseEntity<ApiErrorTo> =
        ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ApiErrorTo(Instant.now().toString(), e.message))

    @ExceptionHandler(
        value = [
            IllegalArgumentException::class,
            ConstraintViolationException::class,
            MethodArgumentNotValidException::class]
    )
    fun onConstraintViolation(e: Exception): ResponseEntity<ApiErrorTo> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiErrorTo(Instant.now().toString(), e.message))

    @ExceptionHandler(ProductDuplicationException::class)
    fun handleProductDuplicationException(e: ProductDuplicationException): ResponseEntity<ApiErrorTo> =
        ResponseEntity.status(HttpStatus.CONFLICT)
            .body(
                ApiErrorTo(
                    Instant.now().toString(),
                    e.message,
                )
            )

    @ExceptionHandler(PersonDuplicationException::class)
    fun handleProductDuplicationException(e: PersonDuplicationException): ResponseEntity<CreatePerson409ErrorTo> =
        ResponseEntity.status(HttpStatus.CONFLICT)
            .body(
                CreatePerson409ErrorTo(
                    e.passportID,
                    Instant.now().toString(),
                    e.message
                )
            )
}
