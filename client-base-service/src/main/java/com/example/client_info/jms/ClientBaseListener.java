package com.example.client_info.jms;

import com.example.client.model.ClientData;
import com.example.client_info.service.ClientBaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientBaseListener {

    private final ClientBaseService clientBaseService;

    @JmsListener(destination = "${spring.jms.template.default-destination}", selector = "await='populateBasicClientInfo'")
    public void receiveClientData(ClientData clientData) {
        log.info("Received message: {}", clientData);
        clientBaseService.processClientData(clientData);
    }
}
