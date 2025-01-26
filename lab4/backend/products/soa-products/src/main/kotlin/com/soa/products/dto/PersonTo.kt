package com.soa.products.dto

import java.io.Serializable

class PersonTo : Serializable {

    companion object {
        private const val serialVersionUID: Long = 1L
    }

    // Properties
    var name: String? = null
    var passportID: String? = null
    var eyeColor: ColorTo? = null
    var hairColor: ColorTo? = null

    // Default constructor
    constructor()

    // Constructor with parameters
    constructor(
        name: String,
        passportID: String,
        eyeColor: ColorTo? = null,
        hairColor: ColorTo? = null
    ) {
        this.name = name
        this.passportID = passportID
        this.eyeColor = eyeColor
        this.hairColor = hairColor
    }
}
