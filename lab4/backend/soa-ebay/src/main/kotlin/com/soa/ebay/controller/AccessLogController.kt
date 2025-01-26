package com.soa.ebay.controller

import com.soa.ebay.logging.dao.AccessLogDao
import com.soa.ebay.logging.model.Log
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AccessLogController(
    private val accessLogDao: AccessLogDao,
) {
    @GetMapping("/logs")
    fun getLastTenLogs(): List<Log> {
        return accessLogDao.findLatestTen()
    }
}
