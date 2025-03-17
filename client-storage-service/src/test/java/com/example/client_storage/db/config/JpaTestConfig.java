package com.example.client_storage.db.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"com.example.client_storage.db.entity"})
@EnableJpaRepositories(basePackages = {"com.example.client_storage.db.repository"})
public class JpaTestConfig {
}
