package com.soa.ebay.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PriceController {
    @PostMapping("/price/increase/{increasePercent}")
    fun increasePriceByPercent(@PathVariable increasePercent: Long): ResponseEntity<Any> {
        //todo

        return ResponseEntity.ok().build()
    }

    @PostMapping("/price/decrease/{decreasePercent}")
    fun decreasePriceByPercent(@PathVariable decreasePercent: String): ResponseEntity<Any> {
        //todo

        return ResponseEntity.ok().build()
    }
}