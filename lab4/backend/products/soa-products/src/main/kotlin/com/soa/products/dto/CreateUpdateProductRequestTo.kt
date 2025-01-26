package com.soa.products.dto

import java.io.Serializable

class CreateUpdateProductRequestTo : Serializable {

    companion object {
        private const val serialVersionUID: Long = 1L
    }

    // Properties with default values
    var name: String? = null

    var coordinates: CoordinatesTo? = null

    var price: Long? = null

    var manufacturerCost: Long? = null

    var unitOfMeasure: UnitOfMeasureTo? = null

    var partNumber: String? = null

    var ownerPassportId: String? = null

    // No-argument constructor
    constructor()

    // Optional: Constructor with all properties for convenient instantiation
    constructor(
        name: String,
        coordinates: CoordinatesTo,
        price: Long,
        manufacturerCost: Long,
        unitOfMeasure: UnitOfMeasureTo,
        partNumber: String? = null,
        ownerPassportId: String? = null
    ) {
        this.name = name
        this.coordinates = coordinates
        this.price = price
        this.manufacturerCost = manufacturerCost
        this.unitOfMeasure = unitOfMeasure
        this.partNumber = partNumber
        this.ownerPassportId = ownerPassportId
    }
}
