package com.soa.products.exception

class ProductNotFoundException(id: Long) : RuntimeException("Product not found with id=$id")
