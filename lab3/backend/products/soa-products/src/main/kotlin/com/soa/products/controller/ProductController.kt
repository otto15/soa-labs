package com.soa.products.controller

import com.soa.products.ejb.domain.PriceOperation
import com.soa.products.ejb.service.ProductService
import com.soa.products.transformer.toCreateProductCommand
import com.soa.products.transformer.toProductTo
import com.soa.products.transformer.toUpdateProductCommand
import com.soa.products.transformer.transform
import generated.soa.products.controller.ProductApi
import generated.soa.products.dto.CreateProductResponseTo
import generated.soa.products.dto.CreateUpdateProductRequestTo
import generated.soa.products.dto.CriteriaTo
import generated.soa.products.dto.FilterOperatorTo
import generated.soa.products.dto.PriceOperationTo
import generated.soa.products.dto.ProductPriceSumTo
import generated.soa.products.dto.ProductTo
import generated.soa.products.dto.SortTypeTo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
class ProductController(
    private val productService: ProductService,
) : ProductApi {
    override fun createProduct(createUpdateProductRequestTo: CreateUpdateProductRequestTo): ResponseEntity<CreateProductResponseTo> =
        ResponseEntity.status(HttpStatus.CREATED)
            .body(
                CreateProductResponseTo(
                    productService.createProduct(createUpdateProductRequestTo.toCreateProductCommand())
                )
            )

    override fun getProductById(id: Long): ResponseEntity<ProductTo> = ResponseEntity.ok(
        productService.getProduct(id).toProductTo()
    )

    override fun deleteProductById(id: Long): ResponseEntity<Unit> {
        productService.deleteProduct(id)
        return ResponseEntity.noContent().build()
    }

    override fun getAllProductPricesSum(): ResponseEntity<ProductPriceSumTo> {
        val totalSum = productService.getTotalPrice()

        return ResponseEntity.ok(ProductPriceSumTo(BigDecimal.valueOf(totalSum)))
    }

    override fun getMinPartNumberProduct(): ResponseEntity<ProductTo> = ResponseEntity.ok(
        productService.getProductWithMinPartNumber().toProductTo()
    )

    override fun getProductsWithManufacturerCostLessThan(
        cost: Long,
        page: Int,
        size: Int
    ): ResponseEntity<List<ProductTo>> {
        return ResponseEntity.ok(
            productService.getProductsWithManufacturerCostLessThan(cost, page, size)
                .map { it.toProductTo() }
        )
    }

    override fun updateProductById(
        id: Long,
        createUpdateProductRequestTo: CreateUpdateProductRequestTo
    ): ResponseEntity<Unit> {
        productService.updateProduct(createUpdateProductRequestTo.toUpdateProductCommand(id))

        return ResponseEntity.ok().build()
    }

    override fun getProducts(
        page: Int,
        size: Int,
        sortCriteriaList: List<CriteriaTo>?,
        sortTypeList: List<SortTypeTo>?,
        filterCriteriaList: List<CriteriaTo>?,
        filterOperators: List<FilterOperatorTo>?,
        filterValues: List<String>?
    ): ResponseEntity<List<ProductTo>> {
        return ResponseEntity.ok(
            productService.getProducts(
                transform(page, size, sortCriteriaList, sortTypeList, filterCriteriaList, filterOperators, filterValues)
            ).map { it.toProductTo() }
        )
    }

    override fun updatePricesOfAllProducts(
        priceOperation: PriceOperationTo,
        percent: BigDecimal
    ): ResponseEntity<Unit> {
        productService.updatePrices(PriceOperation.valueOf(priceOperation.name), percent)

        return ResponseEntity.ok().build()
    }
}
