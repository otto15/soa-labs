package com.soa.products.exception

class PersonNotFoundException(passportID: String) : RuntimeException("Person not found with passportID=$passportID")

class PersonDuplicationException(val passportID: String) :
    RuntimeException("Person already exists with passportID=$passportID")
