package com.soa.products.domain

import java.time.LocalDate

data class Product(
    val id: Long,
    val name: String,
    val coordinates: Coordinates,
    val creationDate: LocalDate,
    val price: Long,
    val partNumber: String?,
    val manufacturerCost: Long,
    val unitOfMeasure: UnitOfMeasure,
    val owner: Person?,
)

data class Coordinates(
    val x: Int,
    val y: Int,
)

enum class UnitOfMeasure {
    KILOGRAMS,
    METERS,
    MILLILITERS,
    MILLIGRAMS,
}
