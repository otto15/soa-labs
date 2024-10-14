package com.soa.products.transformer

import com.soa.products.service.command.CreatePersonCommand
import com.soa.products.domain.Color
import com.soa.products.domain.Person
import generated.soa.products.dto.ColorTo
import generated.soa.products.dto.CreatePersonRequestTo
import generated.soa.products.dto.PersonTo

fun Person.toPersonTo() = PersonTo(
    this.name,
    this.passportID,
    this.eyeColor?.let { ColorTo.forValue(it.name) },
    this.hairColor?.let { ColorTo.forValue(it.name) }
)

fun CreatePersonRequestTo.toCreatePersonCommand() = CreatePersonCommand(
    this.name,
    this.passportID,
    this.eyeColor?.let { Color.valueOf(it.name) },
    this.hairColor?.let { Color.valueOf(it.name) }
)
