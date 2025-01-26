package com.soa.products.dto

import java.io.Serializable

class CreatePersonResponseTo : Serializable {

    companion object {
        private const val serialVersionUID: Long = 1L
    }

    // Property
    var passportID: String? = null

    constructor()

    // Constructor
    constructor(passportID: String) {
        this.passportID = passportID
    }
}
