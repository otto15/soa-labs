package com.soa.products.service

import com.soa.products.dao.ProductDao
import com.soa.products.exception.ProductNotFoundException
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productDao: ProductDao,
) {
    fun getProduct(id: Int) = productDao.findById(id) ?: throw ProductNotFoundException(id)
}