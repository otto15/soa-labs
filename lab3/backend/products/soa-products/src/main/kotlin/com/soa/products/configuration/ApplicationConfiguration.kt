package com.soa.products.configuration

import com.soa.products.ejb.service.PersonService
import com.soa.products.ejb.service.ProductService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain
import java.util.Properties
import javax.naming.Context
import javax.naming.InitialContext

@Configuration
class ApplicationConfiguration {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            authorizeRequests {
                authorize(anyRequest, permitAll)
            }
            cors { disable() }
            csrf { disable() }
        }

        return http.build()
    }

    @Bean
    fun context(): Context {
        val jndiProps = Properties()
        jndiProps[Context.INITIAL_CONTEXT_FACTORY] = "org.wildfly.naming.client.WildFlyInitialContextFactory"
        jndiProps["jboss.naming.client.ejb.context"] = true
        jndiProps["remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED"] = false
        jndiProps["remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS"] = false
        jndiProps["remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT"] = false
        jndiProps[Context.SECURITY_PRINCIPAL] = "user"
        jndiProps[Context.SECURITY_CREDENTIALS] = "user"
        jndiProps[Context.PROVIDER_URL] = "http-remoting://products-ejb:8080"
        
        return InitialContext(jndiProps)
    }

    @Bean
    fun productService(context: Context): ProductService =
        context.lookup("ejb:/soa-products-ejb-0.0.1-SNAPSHOT/ProductServiceImpl!com.soa.products.ejb.service.ProductService")
            as ProductService

    @Bean
    fun personService(context: Context): PersonService =
        context.lookup("ejb:/soa-products-ejb-0.0.1-SNAPSHOT/PersonServiceImpl!com.soa.products.ejb.service.PersonService")
            as PersonService
}
