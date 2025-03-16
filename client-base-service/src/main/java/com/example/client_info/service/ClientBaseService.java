package com.example.client_info.service;

import com.example.client.model.ClientData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientBaseService {

    private final JmsTemplate jmsTemplate;

    public void processClientData(ClientData clientData) {
        // Оновлення даних клієнта
        clientData.setFullName("Іван Іванович");
        log.info("Added fullName: {} for clientId: {}", clientData.getFullName(), clientData.getClientId());

        // Відправка оновлених даних далі у чергу
        jmsTemplate.convertAndSend("client.processing.queue", clientData, message -> {
            message.setStringProperty("awaitStage", "populateClientAddress");
            return message;
        });

        log.info("Sent updated ClientData with awaitStage='populateClientAddress' to 'client.processing.queue'");
    }
}
