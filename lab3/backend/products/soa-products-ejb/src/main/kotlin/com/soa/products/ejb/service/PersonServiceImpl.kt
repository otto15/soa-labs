package com.soa.products.ejb.service

import com.soa.products.ejb.dao.PersonDao
import com.soa.products.ejb.service.command.CreatePersonCommand
import jakarta.ejb.Stateless
import jakarta.inject.Inject

@Stateless
open class PersonServiceImpl : PersonService {
    @Inject
    private lateinit var personDao: PersonDao

    override fun create(createPersonCommand: CreatePersonCommand): String = personDao.insert(createPersonCommand)
}
