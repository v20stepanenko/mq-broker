package com.example.client_contact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@EnableCaching
@EnableFeignClients
public class ClientContactsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientContactsServiceApplication.class, args);
    }
}
