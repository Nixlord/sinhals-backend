package com.pravega.backend.configuration

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource


// Need different params on Heroku.
// Also get from environment variables.
@Configuration
class DataSourceConfiguration {

    fun getEnv(value: String, defaultValue: String): String {
        val res = System.getenv(value);
        if (res != null)
            return res;
        else
            return defaultValue
    }


    fun credential(type: String): String {
        return when(type) {
            "url" -> getEnv("JDBC_DATABASE_URL", "jdbc:pgsql://localhost:5432/dev").replace("jdbc:postgresql", "jdbc:pgsql")
            "username" -> getEnv("JDBC_DATABASE_USERNAME", "postgres")
            "password" -> getEnv("JDBC_DATABASE_PASSWORD", "postgres")
            else -> "ERROR_TYPE"
        }
    }


    @Bean
    fun dataSource(): DataSource {
        return DataSourceBuilder.create()
                .driverClassName("com.impossibl.postgres.jdbc.PGDriver")
                .url(credential("url"))
                .username(credential("username"))
                .password(credential("password"))
                .build();
    }
}