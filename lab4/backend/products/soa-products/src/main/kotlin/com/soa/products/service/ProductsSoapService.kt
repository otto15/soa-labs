package com.soa.products.service

import com.soa.products.dto.CreatePersonRequestTo
import com.soa.products.dto.CreatePersonResponseTo
import com.soa.products.dto.CreateProductResponseTo
import com.soa.products.dto.CreateUpdateProductRequestTo
import com.soa.products.dto.CriteriaTo
import com.soa.products.dto.FilterOperatorTo
import com.soa.products.dto.PriceOperationTo
import com.soa.products.dto.ProductPriceSumTo
import com.soa.products.dto.ProductTo
import com.soa.products.dto.SortTypeTo
import com.soa.products.ejb.domain.PriceOperation
import com.soa.products.ejb.service.PersonService
import com.soa.products.ejb.service.ProductService
import com.soa.products.transformer.toCreatePersonCommand
import com.soa.products.transformer.toCreateProductCommand
import com.soa.products.transformer.toSoapProductTo
import com.soa.products.transformer.toUpdateProductCommand
import com.soa.products.transformer.transform
import jakarta.jws.WebMethod
import jakarta.jws.WebParam
import jakarta.jws.WebService
import org.springframework.stereotype.Controller
import java.math.BigDecimal

@WebService(name = "products")
@Controller
class ProductsSoapService(
    private val productService: ProductService,
    private val personService: PersonService,
) {
    @WebMethod
    fun createProduct(
        @WebParam(name = "createUpdateProductRequestTo") createUpdateProductRequestTo: CreateUpdateProductRequestTo
    ): CreateProductResponseTo =
        CreateProductResponseTo(
            productService.createProduct(createUpdateProductRequestTo.toCreateProductCommand())
        )

    @WebMethod
    fun getProductById(@WebParam(name = "id") id: Long): ProductTo = productService.getProduct(id).toSoapProductTo()

    @WebMethod
    fun deleteProductById(@WebParam(name = "id") id: Long) {
        productService.deleteProduct(id)
    }

    @WebMethod
    fun getAllProductPricesSum(): ProductPriceSumTo {
        val totalSum = productService.getTotalPrice()

        return ProductPriceSumTo(BigDecimal.valueOf(totalSum))
    }

    @WebMethod
    fun getMinPartNumberProduct(): ProductTo = productService.getProductWithMinPartNumber().toSoapProductTo()

    @WebMethod
    fun getProductsWithManufacturerCostLessThan(
        @WebParam(name = "cost") cost: Long,
        @WebParam(name = "page") page: Int,
        @WebParam(name = "size") size: Int
    ): List<ProductTo> = productService.getProductsWithManufacturerCostLessThan(cost, page, size)
        .map { it.toSoapProductTo() }

    @WebMethod
    fun updateProductById(
        @WebParam(name = "id") id: Long,
        @WebParam(name = "createUpdateProductRequestTo") createUpdateProductRequestTo: CreateUpdateProductRequestTo
    ) { productService.updateProduct(createUpdateProductRequestTo.toUpdateProductCommand(id)) }

    @WebMethod
    fun getProducts(
        @WebParam(name = "page") page: Int,
        @WebParam(name = "size") size: Int,
        @WebParam(name = "sortCriteriaList") sortCriteriaList: List<CriteriaTo>?,
        @WebParam(name = "sortTypeList") sortTypeList: List<SortTypeTo>?,
        @WebParam(name = "filterCriteriaList") filterCriteriaList: List<CriteriaTo>?,
        @WebParam(name = "filterOperators") filterOperators: List<FilterOperatorTo>?,
        @WebParam(name = "filterValues") filterValues: List<String>?
    ): List<ProductTo> {
        return productService.getProducts(
                transform(page, size, sortCriteriaList, sortTypeList, filterCriteriaList, filterOperators, filterValues)
            ).map { it.toSoapProductTo() }
    }

    @WebMethod
    fun updatePricesOfAllProducts(
        @WebParam(name = "priceOperation") priceOperation: PriceOperationTo,
        @WebParam(name = "percent") percent: BigDecimal
    ) { productService.updatePrices(PriceOperation.valueOf(priceOperation.name), percent) }

    @WebMethod
    fun createPerson(
        @WebParam(name = "createPersonRequestTo") createPersonRequestTo: CreatePersonRequestTo
    ): CreatePersonResponseTo =
        CreatePersonResponseTo(personService.create(createPersonRequestTo.toCreatePersonCommand()))
}
