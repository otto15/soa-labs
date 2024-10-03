package com.soa.products.controller

import com.soa.products.service.ProductService
import generated.soa.products.controller.ProductApi
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productService: ProductService,
): ProductApi
