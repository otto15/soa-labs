package com.soa.products.exception

class ProductNotFoundException(id: Int) : RuntimeException("Product not found with id=$id")