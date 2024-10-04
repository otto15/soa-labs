package com.soa.products.exception

class PersonNotFoundException(passportID: String) : RuntimeException("Person not found with passportID=$passportID")
