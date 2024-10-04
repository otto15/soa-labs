package com.soa.products.transformer

import com.soa.products.command.CreateProductCommand
import com.soa.products.domain.Coordinates
import com.soa.products.domain.Product
import com.soa.products.domain.UnitOfMeasure
import generated.soa.products.dto.CoordinatesTo
import generated.soa.products.dto.CreateUpdateProductRequestTo
import generated.soa.products.dto.ProductTo
import generated.soa.products.dto.UnitOfMeasureTo

fun CreateUpdateProductRequestTo.toCreateUpdateProductCommand() = CreateProductCommand(
    this.name,
    this.coordinates.toCoordinates(),
    this.price,
    this.manufacturerCost,
    UnitOfMeasure.valueOf(this.unitOfMeasure.value),
    this.partNumber,
    this.ownerPassportId
)

fun CoordinatesTo.toCoordinates() = Coordinates(this.x, this.y)

fun Coordinates.toCoordinatesTo() = CoordinatesTo(this.x, this.y)

fun Product.toProductTo() = ProductTo(
    this.id,
    this.name,
    this.coordinates.toCoordinatesTo(),
    this.creationDate,
    this.price,
    this.manufacturerCost,
    UnitOfMeasureTo.valueOf(this.unitOfMeasure.name),
    this.partNumber,
    this.owner?.toPersonTo()
)
