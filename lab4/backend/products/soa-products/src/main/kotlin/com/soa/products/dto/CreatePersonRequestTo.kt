package com.soa.products.dto

import java.io.Serializable

class CreatePersonRequestTo : Serializable {

    companion object {
        private const val serialVersionUID: Long = 1L
    }

    // Properties
    var name: String? = null
    var passportID: String? = null
    var eyeColor: ColorTo? = null
    var hairColor: ColorTo? = null

    // Constructor with required fields
    constructor()

    // Constructor with all fields
    constructor(name: String, passportID: String, eyeColor: ColorTo?, hairColor: ColorTo?) {
        this.name = name
        this.passportID = passportID
        this.eyeColor = eyeColor
        this.hairColor = hairColor
    }
}
