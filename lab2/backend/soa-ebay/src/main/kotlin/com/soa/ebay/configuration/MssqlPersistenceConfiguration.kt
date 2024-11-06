package com.soa.ebay.configuration

import com.soa.ebay.logging.dao.AccessLogDao
import com.soa.ebay.logging.dao.MssqlAccessLogDao
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import javax.sql.DataSource

@Configuration
@Profile("mssql")
class MssqlPersistenceConfiguration {
    @Bean
    @ConfigurationProperties("spring.mssql.datasource")
    fun mssqlDataSourceProperties() = DataSourceProperties()

    @Bean
    fun mssqlDataSource(): DataSource = mssqlDataSourceProperties()
        .initializeDataSourceBuilder()
        .build()

    @Bean
    fun mssqlJdbcTemplate(@Qualifier("mssqlDataSource") dataSource: DataSource) =
        NamedParameterJdbcTemplate(dataSource)

    @Bean
    fun mssqlAccessLogDao(mssqlJdbcTemplate: NamedParameterJdbcTemplate): AccessLogDao {
        return MssqlAccessLogDao(mssqlJdbcTemplate)
    }
}
