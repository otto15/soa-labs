package com.soa.products.service

import jakarta.jws.WebMethod
import jakarta.jws.WebParam
import jakarta.jws.WebService
import org.springframework.stereotype.Controller

@WebService(name = "hello")
@Controller
open class HelloService {
    @WebMethod
    open fun sayHello(
        @WebParam(name = "hello") hello: Hello
    ) : HelloResponse {
        val helloResponse: HelloResponse = HelloResponse()
        helloResponse.setA("rama")
        helloResponse.setB(false)

        return helloResponse
    }
}

class Hello {
    private var a: Int = 0
    private lateinit var b: String

    fun getA(): Int {
        return a
    }

    fun setA(value: Int) {
        a = value
    }

    fun getB(): String {
        return b
    }

    fun setB(value: String) {
        b = value
    }
}

class HelloResponse {
    private lateinit var a: String
    private var b: Boolean = false

    fun getA(): String {
        return a
    }

    fun setA(value: String) {
        a = value
    }

    fun getB(): Boolean {
        return b
    }

    fun setB(value: Boolean) {
        b = value
    }
}
