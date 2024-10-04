package com.soa.products.command

import com.soa.products.domain.Color

data class CreatePersonCommand(
    val name: String,
    val passportID: String,
    val eyeColor: Color?,
    val hairColor: Color?,
)
