package com.soa.products.rest.adapter.controller

import com.soa.products.rest.adapter.client.ProductsSoapClient
import com.soa.products.rest.adapter.transformer.toRestProductTo
import com.soa.products.rest.adapter.transformer.toSoapCreateUpdateProductRequestTo
import com.soa.products.rest.adapter.transformer.transform
import generated.soa.products.rest.adapter.controller.ProductApi
import generated.soa.products.rest.adapter.dto.CreateProductResponseTo
import generated.soa.products.rest.adapter.dto.CreateUpdateProductRequestTo
import generated.soa.products.rest.adapter.dto.CriteriaTo
import generated.soa.products.rest.adapter.dto.FilterOperatorTo
import generated.soa.products.rest.adapter.dto.PriceOperationTo
import generated.soa.products.rest.adapter.dto.ProductPriceSumTo
import generated.soa.products.rest.adapter.dto.ProductTo
import generated.soa.products.rest.adapter.dto.SortTypeTo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
class ProductController(
    private val productsSoapClient: ProductsSoapClient,
) : ProductApi {
    override fun createProduct(createUpdateProductRequestTo: CreateUpdateProductRequestTo): ResponseEntity<CreateProductResponseTo> =
        ResponseEntity.status(HttpStatus.CREATED)
            .body(
                CreateProductResponseTo(
                    productsSoapClient.createProduct(createUpdateProductRequestTo.toSoapCreateUpdateProductRequestTo())
                )
            )

    override fun getProductById(id: Long): ResponseEntity<ProductTo> = ResponseEntity.ok(
        productsSoapClient.getProductById(id).toRestProductTo()
    )

    override fun deleteProductById(id: Long): ResponseEntity<Unit> {
        productsSoapClient.deleteProduct(id)
        return ResponseEntity.noContent().build()
    }

    override fun getAllProductPricesSum(): ResponseEntity<ProductPriceSumTo> {
        val totalSum = productsSoapClient.getTotalPrice()

        return ResponseEntity.ok(ProductPriceSumTo(totalSum))
    }

    override fun getMinPartNumberProduct(): ResponseEntity<ProductTo> = ResponseEntity.ok(
        productsSoapClient.getProductWithMinPartNumber().toRestProductTo()
    )

    override fun getProductsWithManufacturerCostLessThan(
        cost: Long,
        page: Int,
        size: Int
    ): ResponseEntity<List<ProductTo>> {
        return ResponseEntity.ok(
            productsSoapClient.getProductsWithManufacturerCostLessThan(cost, page, size).map {
                it.toRestProductTo()
            }
        )
    }

    override fun updateProductById(
        id: Long,
        createUpdateProductRequestTo: CreateUpdateProductRequestTo
    ): ResponseEntity<Unit> {
        productsSoapClient.updateProductById(id, createUpdateProductRequestTo.toSoapCreateUpdateProductRequestTo())

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
            productsSoapClient.getProducts(
                transform(page, size, sortTypeList, sortCriteriaList, filterCriteriaList, filterOperators, filterValues)
            ).map { it.toRestProductTo() }
        )
    }

    override fun updatePricesOfAllProducts(
        priceOperation: PriceOperationTo,
        percent: BigDecimal
    ): ResponseEntity<Unit> {
        productsSoapClient.updatePrices(priceOperation.value, percent)

        return ResponseEntity.ok().build()
    }
}
