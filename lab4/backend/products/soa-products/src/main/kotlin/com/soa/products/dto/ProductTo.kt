package com.soa.products.dto

import java.io.Serializable
import java.time.LocalDate

class ProductTo : Serializable {

    companion object {
        private const val serialVersionUID: Long = 1L
    }

    // Properties
    var id: Long? = null
    var name: String? = null
    var coordinates: CoordinatesTo? = null
    var createdDate: String? = null
    var price: Long? = null
    var manufacturerCost: Long? = null
    var unitOfMeasure: UnitOfMeasureTo? = null
    var partNumber: String? = null
    var owner: PersonTo? = null

    // Default constructor
    constructor()

    // Full constructor
    constructor(
        id: Long,
        name: String,
        coordinates: CoordinatesTo,
        createdDate: LocalDate,
        price: Long,
        manufacturerCost: Long,
        unitOfMeasure: UnitOfMeasureTo,
        partNumber: String? = null,
        owner: PersonTo? = null
    ) {
        this.id = id
        this.name = name
        this.coordinates = coordinates
        this.createdDate = createdDate.toString()
        this.price = price
        this.manufacturerCost = manufacturerCost
        this.unitOfMeasure = unitOfMeasure
        this.partNumber = partNumber
        this.owner = owner
    }
}

