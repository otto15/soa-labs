package com.soa.products.dto

enum class UnitOfMeasureTo(val value: String) {

    KILOGRAMS("KILOGRAMS"),

    METERS("METERS"),

    MILLILITERS("MILLILITERS"),

    MILLIGRAMS("MILLIGRAMS");

    companion object {
        fun forValue(value: String): UnitOfMeasureTo {
            return values().first { it.value == value }
        }
    }
}
