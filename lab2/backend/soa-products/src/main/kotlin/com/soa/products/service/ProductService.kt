package com.soa.products.service

import com.soa.products.service.command.CreateProductCommand
import com.soa.products.service.command.UpdateProductCommand
import com.soa.products.dao.ProductDao
import com.soa.products.domain.Product
import com.soa.products.domain.ProductSearchParams
import com.soa.products.exception.ProductWithMinPartNumberNotFound
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

    fun getProductWithMinPartNumber(): Product {
        return productDao.findProductWithMinPartNumber()
            ?: throw ProductWithMinPartNumberNotFound()
    }

    fun getProductsWithManufacturerCostLessThan(cost: Long, page: Int, size: Int): List<Product> {
        val offset = (page - 1) * size
        return productDao.findProductsWithManufacturerCostLessThan(cost, offset, size)
    }

    @Transactional
    fun updateProduct(updateProductCommand: UpdateProductCommand) {
        updateProductCommand.ownerPassportId?.let {
            if (!personService.existsById(it)) {
                throw PersonNotFoundException(it)
            }
        }

        if (!productDao.update(updateProductCommand)) {
            throw ProductNotFoundException(updateProductCommand.id)
        }
    }

    fun getProducts(productSearchParams: ProductSearchParams): List<Product> = productDao.find(productSearchParams)
}
