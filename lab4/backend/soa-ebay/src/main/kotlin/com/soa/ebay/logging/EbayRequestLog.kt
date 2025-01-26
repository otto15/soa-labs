package com.soa.ebay.logging

import com.soa.ebay.logging.dao.AccessLogDao
import com.soa.ebay.logging.model.Log
import org.eclipse.jetty.server.CustomRequestLog
import org.eclipse.jetty.server.Request
import org.eclipse.jetty.server.Response
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class EbayRequestLog(
    private val accessLogDao: AccessLogDao
) : CustomRequestLog() {
    override fun log(request: Request, response: Response) {
        super.log(request, response)

        accessLogDao.insert(Log(Instant.now(), request.method, request.httpURI.asString(), response.status.toString()))
    }
}