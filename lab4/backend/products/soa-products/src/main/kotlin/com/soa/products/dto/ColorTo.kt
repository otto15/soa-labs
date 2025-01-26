package com.soa.products.dto

enum class ColorTo(val value: String) {
    YELLOW("YELLOW"),
    WHITE("WHITE"),
    BROWN("BROWN"),
    GREEN("GREEN"),
    RED("RED"),
    BLACK("BLACK"),
    ORANGE("ORANGE");

    companion object {
        fun forValue(value: String): ColorTo {
            return values().firstOrNull { it.value == value }
                ?: throw IllegalArgumentException("No enum constant with value $value")
        }
    }
}
