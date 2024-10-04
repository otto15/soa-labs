package com.soa.products.service

import com.soa.products.command.CreatePersonCommand
import com.soa.products.dao.PersonDao
import org.springframework.stereotype.Service

@Service
class PersonService(
    private val personDao: PersonDao,
) {
    fun create(createPersonCommand: CreatePersonCommand): String = personDao.insert(createPersonCommand)

    fun existsById(passportID: String): Boolean {
        return personDao.existsById(passportID)
    }
}
