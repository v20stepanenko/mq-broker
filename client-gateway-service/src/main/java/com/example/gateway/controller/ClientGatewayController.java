package com.example.gateway.controller;

import com.example.client.generated.model.ClientData;
import com.example.gateway.service.ClientMessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ClientGatewayController {

    private final ClientMessageProducer clientMessageProducer;

    @PostMapping("/client-collect-info")
    public ResponseEntity<String> processClientRequest(@RequestBody ClientData clientData) {
        log.info("Received client request: {}", clientData);
        clientMessageProducer.sendMessagePopulateFullName(clientData);
        var responseMessage = "[Client ID: %s] Data collection has started. Awaiting further processing...".formatted(clientData.getClientId());

        return ResponseEntity.ok(responseMessage);
    }
}
