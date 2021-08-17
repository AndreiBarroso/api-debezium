package com.andreibarroso.apidebezium.config;

import com.andreibarroso.apidebezium.entity.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DebeziumConnectorConfig {

    /**
     * Database details.
     */
    @Value("${customer.datasource.host}")
    private String customerDbHost;

    @Value("${customer.datasource.database}")
    private String customerDbName;

    @Value("${customer.datasource.port}")
    private String customerDbPort;

    @Value("${customer.datasource.username}")
    private String customerDbUsername;

    @Value("${customer.datasource.password}")
    private String customerDbPassword;

    /**
     * Customer Database Connector Configuration
     */


    @Bean
    public io.debezium.config.Configuration customerConnector() {
        return io.debezium.config.Configuration.create()
                .with("name", "customer-oracle-connector")
                .with("connector.class", "io.debezium.connector.postgresql.PostgresConnector")
                .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename", "/tmp/offsets.dat")
                .with("database.sslmode", "disable")
                .with("offset.flush.interval.ms", "60000")
                .with("database.hostname", customerDbHost)
                .with("database.port", customerDbPort)
                .with("database.user", customerDbUsername)
                .with("database.password", customerDbPassword)
                .with("database.dbname", customerDbName)
                .with("database.include.list", customerDbName)
                .with("database.server.name", "customer-oracle-db-server")
                .build();
    }
}
