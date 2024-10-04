package com.soa.products.controller

import com.soa.products.service.PersonService
import com.soa.products.transformer.toCreatePersonCommand
import generated.soa.products.controller.PersonApi
import generated.soa.products.dto.CreatePersonRequestTo
import generated.soa.products.dto.CreatePersonResponseTo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["*"])
@RestController
class PersonController(
    private val personService: PersonService,
) : PersonApi {
    override fun createPerson(createPersonRequestTo: CreatePersonRequestTo): ResponseEntity<CreatePersonResponseTo> =
        ResponseEntity.status(HttpStatus.CREATED)
            .body(CreatePersonResponseTo(personService.create(createPersonRequestTo.toCreatePersonCommand())))
}
