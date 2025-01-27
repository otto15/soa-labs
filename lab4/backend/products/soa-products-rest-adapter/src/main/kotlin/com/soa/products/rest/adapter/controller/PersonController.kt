package com.soa.products.rest.adapter.controller

import com.soa.products.rest.adapter.client.ProductsSoapClient
import com.soa.products.rest.adapter.wsdl.ColorTo
import generated.soa.products.rest.adapter.controller.PersonApi
import generated.soa.products.rest.adapter.dto.CreatePersonRequestTo
import generated.soa.products.rest.adapter.dto.CreatePersonResponseTo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import com.soa.products.rest.adapter.wsdl.CreatePersonRequestTo as SoapCreatePersonRequestTo

@RestController
class PersonController(
    private val productsSoapClient: ProductsSoapClient,
) : PersonApi {
    override fun createPerson(createPersonRequestTo: CreatePersonRequestTo): ResponseEntity<CreatePersonResponseTo> =
        ResponseEntity.status(HttpStatus.CREATED)
            .body(
                CreatePersonResponseTo(
                    productsSoapClient.createPerson(
                        createPersonRequestTo.let {
                            val req = SoapCreatePersonRequestTo()
                            req.name = it.name
                            req.passportID = it.passportID
                            req.eyeColor = it.eyeColor?.let { eyeColor -> ColorTo.fromValue(eyeColor.value) }
                            req.hairColor = it.hairColor?.let { hairColor -> ColorTo.fromValue(hairColor.value) }

                            req
                        }
                    )
                )
            )
}
