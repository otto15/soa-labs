package com.soa.products.dto

import java.io.Serializable
import java.math.BigDecimal

class ProductPriceSumTo : Serializable {

    companion object {
        private const val serialVersionUID: Long = 1L
    }

    // Property
    var sum: BigDecimal? = null

    // Default constructor
    constructor()

    // Constructor with parameters
    constructor(sum: BigDecimal) {
        this.sum = sum
    }
}
