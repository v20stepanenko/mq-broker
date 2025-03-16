package com.example.client_info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ClientBaseServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientBaseServiceApplication.class, args);
    }
}
