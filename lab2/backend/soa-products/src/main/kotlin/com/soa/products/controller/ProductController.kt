package com.soa.products.controller

import com.soa.products.service.ProductService
import com.soa.products.transformer.toCreateUpdateProductCommand
import com.soa.products.transformer.toProductTo
import generated.soa.products.controller.ProductApi
import generated.soa.products.dto.CreateProductResponseTo
import generated.soa.products.dto.CreateUpdateProductRequestTo
import generated.soa.products.dto.ProductTo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productService: ProductService,
) : ProductApi {
    override fun createProduct(createUpdateProductRequestTo: CreateUpdateProductRequestTo): ResponseEntity<CreateProductResponseTo> =
        ResponseEntity.status(HttpStatus.CREATED)
            .body(
                CreateProductResponseTo(
                    productService.createProduct(createUpdateProductRequestTo.toCreateUpdateProductCommand())
                )
            )

    override fun getProductById(id: Long): ResponseEntity<ProductTo> =
        ResponseEntity.ok(
            productService.getProduct(id).toProductTo()
        )
}
