package com.soa.products.dto

import java.io.Serializable

class CoordinatesTo : Serializable {

    companion object {
        private const val serialVersionUID: Long = 1L
    }

    var x: Int = 0

    var y: Int = 0

    // No-argument constructor
    constructor()

    // Optional: Constructor with all properties for convenient instantiation
    constructor(x: Int, y: Int) {
        this.x = x
        this.y = y
    }
}
