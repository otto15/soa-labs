package com.soa.products.dto

import java.time.Instant

data class ApiErrorTo(
    val message: String?,
    val timestamp: Instant = Instant.now()
)
