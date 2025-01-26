package com.soa.products.dto

enum class PriceOperationTo(val value: String) {

    INCREASE("INCREASE"),
    DECREASE("DECREASE");

    companion object {
        fun forValue(value: String): generated.soa.products.dto.PriceOperationTo {
            return generated.soa.products.dto.PriceOperationTo.values().first{ it.value == value}
        }
    }
}
