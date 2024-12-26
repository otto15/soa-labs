package com.soa.ebay.controller

import com.soa.ebay.client.ProductsClient
import generated.soa.ebay.controller.PricesApi
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
class PriceController(
    val productsClient: ProductsClient
) : PricesApi {
    override fun decreasePrices(decreasePercent: BigDecimal): ResponseEntity<Unit> {
        productsClient.decreasePrices(decreasePercent)

        return ResponseEntity.ok().build()
    }

    override fun increasePrices(increasePercent: BigDecimal): ResponseEntity<Unit> {
        productsClient.increasePrices(increasePercent)

        return ResponseEntity.ok().build()
    }
}
