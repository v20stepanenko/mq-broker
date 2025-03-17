package com.example.client_storage.jms;

import com.example.client.generated.model.ClientData;
import com.example.client_storage.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import static com.example.client_storage.common.Constants.SELECTOR;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientListener {
    private final ClientService clientService;

    @JmsListener(destination = "${spring.jms.template.default-destination}", selector = SELECTOR)
    public void receiveClientData(ClientData clientData) {
        clientService.saveClientData(clientData);
    }
}
