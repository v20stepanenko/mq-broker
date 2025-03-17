package com.example.client_cards.jms;

import com.example.client.generated.model.ClientData;
import com.example.client_cards.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import static com.example.client_cards.common.Constants.SELECTOR;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientListener {
    private final ClientService clientService;

    @JmsListener(destination = "${spring.jms.template.default-destination}", selector = SELECTOR)
    public void receiveClientData(ClientData clientData) {
        clientService.processClientData(clientData);
    }
}
