package com.soa.products.service

import com.soa.products.command.CreateProductCommand
import com.soa.products.dao.ProductDao
import com.soa.products.exception.PersonNotFoundException
import com.soa.products.exception.ProductNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    private val personService: PersonService,
    private val productDao: ProductDao,
) {
    @Transactional
    fun createProduct(createUpdateProductCommand: CreateProductCommand): Long {
        createUpdateProductCommand.ownerPassportId?.let {
            if (!personService.existsById(it)) {
                throw PersonNotFoundException(it)
            }
        }

        return productDao.insert(createUpdateProductCommand)
    }

    fun getProduct(id: Long) = productDao.findById(id) ?: throw ProductNotFoundException(id)

    @Transactional
    fun deleteProduct(id: Long) {
        if (!productDao.deleteById(id)) {
            throw ProductNotFoundException(id)
        }
    }

    fun getTotalPrice(): Long {
        return productDao.getTotalPrice()
    }
}
