package com.soa.products.dto

enum class SortTypeTo(val value: String) {

    ASC("ASC"),
    DESC("DESC");

    companion object {
        fun forValue(value: String): SortTypeTo {
            return values().firstOrNull { it.value == value }
                ?: throw IllegalArgumentException("No enum constant with value $value")
        }
    }
}
