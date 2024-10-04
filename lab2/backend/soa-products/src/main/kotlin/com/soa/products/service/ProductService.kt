package com.soa.products.service

import com.soa.products.command.CreateProductCommand
import com.soa.products.dao.ProductDao
import com.soa.products.domain.Product
import com.soa.products.exception.PersonNotFoundException
import com.soa.products.exception.ProductNotFoundException
import com.soa.products.transformer.toProductTo
import generated.soa.products.dto.ProductTo
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

    fun getProductWithMinPartNumber(): Product {
        return productDao.findProductWithMinPartNumber()
            ?: throw IllegalStateException("No product with a minimum partNumber found")
    }

    fun getProductsWithManufacturerCostLessThan(cost: Long, page: Long, size: Long): List<ProductTo> {
        val offset = (page - 1) * size
        return productDao.findProductsWithManufacturerCostLessThan(cost, offset, size)
            .map { it: Product -> it.toProductTo() }
    }
}
