package com.example.gateway.service;

import com.example.client.generated.model.ClientData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import static com.example.gateway.common.Constants.AWAIT_ATTRIBUTE;
import static com.example.gateway.common.Constants.POPULATE_CLIENT_FULL_NAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientMessageProducer {

    private final JmsTemplate jmsTemplate;

    @Value("${spring.jms.template.default-destination}")
    private String jmsDestination;

    public void sendMessagePopulateFullName(ClientData clientData) {
        jmsTemplate.convertAndSend(jmsDestination, clientData, message -> {
            message.setStringProperty(AWAIT_ATTRIBUTE, POPULATE_CLIENT_FULL_NAME);
            return message;
        });
        log.info("Message queued to '{}' for client ID: {} | Awaiting stage: {}",
                 jmsDestination, clientData.getClientId(), POPULATE_CLIENT_FULL_NAME);
    }
}
