package com.example.gateway.messagin.controller;

import com.example.client.model.ClientData;
import com.example.gateway.messagin.model.ClientRequest;
import com.example.gateway.messagin.service.ClientMessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/client-gateway")
@RequiredArgsConstructor
public class ClientGatewayController {

    private final ClientMessageProducer clientMessageProducer;

    @PostMapping
    public ResponseEntity<?> processClientRequest(@RequestBody ClientData clientData,
                                                  @RequestHeader(value = "sid", required = false) String sid) {
        if (!isValidSid(sid)) {
            log.warn("Invalid SID received: {}", sid);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid SID");
        }

        log.info("Received client request: {}", clientData);
        clientMessageProducer.sendMessage(clientData);
        return ResponseEntity.ok("Client request sent to processing queue");
    }

    private boolean isValidSid(String sid) {
        return "valid-sid".equals(sid);
    }
}
