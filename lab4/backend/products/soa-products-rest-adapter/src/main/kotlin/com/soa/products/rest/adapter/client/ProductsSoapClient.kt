package com.soa.products.rest.adapter.client

import com.soa.products.rest.adapter.wsdl.CreatePerson
import com.soa.products.rest.adapter.wsdl.CreatePersonRequestTo
import com.soa.products.rest.adapter.wsdl.CreatePersonResponse
import com.soa.products.rest.adapter.wsdl.CreateProduct
import com.soa.products.rest.adapter.wsdl.CreateProductResponse
import com.soa.products.rest.adapter.wsdl.CreateUpdateProductRequestTo
import com.soa.products.rest.adapter.wsdl.DeleteProductById
import com.soa.products.rest.adapter.wsdl.DeleteProductByIdResponse
import com.soa.products.rest.adapter.wsdl.GetAllProductPricesSum
import com.soa.products.rest.adapter.wsdl.GetAllProductPricesSumResponse
import com.soa.products.rest.adapter.wsdl.GetMinPartNumberProduct
import com.soa.products.rest.adapter.wsdl.GetMinPartNumberProductResponse
import com.soa.products.rest.adapter.wsdl.GetProductById
import com.soa.products.rest.adapter.wsdl.GetProductByIdResponse
import com.soa.products.rest.adapter.wsdl.GetProducts
import com.soa.products.rest.adapter.wsdl.GetProductsResponse
import com.soa.products.rest.adapter.wsdl.GetProductsWithManufacturerCostLessThan
import com.soa.products.rest.adapter.wsdl.GetProductsWithManufacturerCostLessThanResponse
import com.soa.products.rest.adapter.wsdl.PriceOperationTo
import com.soa.products.rest.adapter.wsdl.ProductTo
import com.soa.products.rest.adapter.wsdl.UpdatePricesOfAllProducts
import com.soa.products.rest.adapter.wsdl.UpdateProductById
import com.soa.products.rest.adapter.wsdl.UpdateProductByIdResponse
import jakarta.xml.bind.JAXBElement
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.ws.client.core.support.WebServiceGatewaySupport
import java.math.BigDecimal

@Component
class ProductsSoapClient(
    private val host: String,
    private val baseUrl: String = "http://$host:1616/services/products"
) : WebServiceGatewaySupport() {
    fun getProductById(id: Long): ProductTo {
        val request = GetProductById()
        request.id = id

        log.info("Requesting product for id: $id")

        val response: JAXBElement<GetProductByIdResponse> = webServiceTemplate
            .marshalSendAndReceive(
                baseUrl,
                request
            ) as JAXBElement<GetProductByIdResponse>

        return response.value.`return`
    }

    fun createProduct(req: CreateUpdateProductRequestTo): Long {
        val request = CreateProduct()
        request.createUpdateProductRequestTo = req

        val response: JAXBElement<CreateProductResponse> = webServiceTemplate
            .marshalSendAndReceive(
                baseUrl,
                request
            ) as JAXBElement<CreateProductResponse>

        return response.value.`return`.id
    }

    fun deleteProduct(id: Long) {
        val request = DeleteProductById()
        request.id = id

        webServiceTemplate.marshalSendAndReceive(
            baseUrl,
            request
        ) as JAXBElement<DeleteProductByIdResponse>
    }

    fun getTotalPrice(): BigDecimal {
        val request = GetAllProductPricesSum()

        val response = webServiceTemplate.marshalSendAndReceive(
            baseUrl,
            request
        ) as JAXBElement<GetAllProductPricesSumResponse>

        return response.value.`return`.sum
    }

    fun getProductWithMinPartNumber(): ProductTo {
        val request = GetMinPartNumberProduct()

        val response = webServiceTemplate.marshalSendAndReceive(
            baseUrl,
            request
        ) as JAXBElement<GetMinPartNumberProductResponse>

        return response.value.`return`
    }

    fun getProductsWithManufacturerCostLessThan(
        cost: Long,
        page: Int,
        size: Int
    ): List<ProductTo> {
        val request = GetProductsWithManufacturerCostLessThan()
        request.cost = cost
        request.page = page
        request.size = size

        val response = webServiceTemplate.marshalSendAndReceive(
            baseUrl,
            request
        ) as JAXBElement<GetProductsWithManufacturerCostLessThanResponse>

        return response.value.`return`
    }

    fun updateProductById(id: Long, req: CreateUpdateProductRequestTo) {
        val request = UpdateProductById()
        request.id = id
        request.createUpdateProductRequestTo = req

        webServiceTemplate.marshalSendAndReceive(
            baseUrl,
            request
        ) as JAXBElement<UpdateProductByIdResponse>
    }

    fun getProducts(request: GetProducts): List<ProductTo> {
        val response = webServiceTemplate.marshalSendAndReceive(
            baseUrl,
            request
        ) as JAXBElement<GetProductsResponse>

        return response.value.`return`
    }

    fun updatePrices(priceOperation: String, percent: BigDecimal) {
        val request = UpdatePricesOfAllProducts()
        request.priceOperation = PriceOperationTo.fromValue(priceOperation)
        request.percent = percent

        webServiceTemplate.marshalSendAndReceive(
            baseUrl,
            request
        )
    }

    fun createPerson(req: CreatePersonRequestTo): String {
        val request = CreatePerson()
        request.createPersonRequestTo = req

        val response = webServiceTemplate.marshalSendAndReceive(
            baseUrl,
            request
        ) as JAXBElement<CreatePersonResponse>

        return response.value.`return`.passportID
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(ProductsSoapClient::class.java)
    }
}