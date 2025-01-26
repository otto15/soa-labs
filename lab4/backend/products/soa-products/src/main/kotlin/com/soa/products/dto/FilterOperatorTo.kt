package com.soa.products.dto

enum class FilterOperatorTo(val value: String) {

    EQUAL("EQUAL"),
    LESS("LESS"),
    GREATER("GREATER"),
    LESS_OR_EQUAL("LESS_OR_EQUAL"),
    GREATER_OR_EQUAL("GREATER_OR_EQUAL"),
    SAME("SAME");

    companion object {
        fun forValue(value: String): FilterOperatorTo {
            return values().firstOrNull { it.value == value }
                ?: throw IllegalArgumentException("No enum constant with value $value")
        }
    }
}
