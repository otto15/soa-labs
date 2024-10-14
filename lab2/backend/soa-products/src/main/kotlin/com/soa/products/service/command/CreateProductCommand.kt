package com.soa.products.service.command

import com.soa.products.domain.Coordinates
import com.soa.products.domain.UnitOfMeasure

data class CreateProductCommand(
    val name: String,
    val coordinates: Coordinates,
    val price: Long,
    val manufacturerCost: Long,
    val unitOfMeasure: UnitOfMeasure,
    val partNumber: String?,
    val ownerPassportId: String?,
)
