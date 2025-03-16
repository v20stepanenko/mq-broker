package com.example.gateway.messagin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ClientGatewayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientGatewayServiceApplication.class, args);
    }
}
