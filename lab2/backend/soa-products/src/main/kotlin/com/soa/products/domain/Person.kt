package com.soa.products.domain

data class Person(
    val name: String,
    val passportID: String,
    val eyeColor: Color?,
    val hairColor: Color?,
)

enum class Color {
    YELLOW,
    WHITE,
    BROWN,
    GREEN,
    RED,
    BLACK,
    ORANGE,
}
