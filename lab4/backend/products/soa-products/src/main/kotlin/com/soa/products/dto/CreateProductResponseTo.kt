package com.soa.products.dto

import java.io.Serializable

class CreateProductResponseTo : Serializable {

    companion object {
        private const val serialVersionUID: Long = 1L
    }

    var id: Long = 0

    // No-argument constructor
    constructor()

    // Optional: Constructor for convenient instantiation
    constructor(id: Long) {
        this.id = id
    }
}
