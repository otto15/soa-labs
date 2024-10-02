package com.soa.products.controller

import com.soa.products.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productService: ProductService,
) {
    @GetMapping("/products/{id}")
    fun getProduct(@PathVariable id: Int) {
        productService.getProduct(id)
    }
}