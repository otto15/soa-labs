package com.soa.ebay.logging.model

import java.time.Instant

data class Log(
    val timestamp: Instant,
    val requestMethod: String,
    val requestUrl: String,
    val status: String
)
