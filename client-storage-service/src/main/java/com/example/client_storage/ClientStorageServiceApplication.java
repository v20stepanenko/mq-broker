package com.example.client_storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ClientStorageServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientStorageServiceApplication.class, args);
    }
}
