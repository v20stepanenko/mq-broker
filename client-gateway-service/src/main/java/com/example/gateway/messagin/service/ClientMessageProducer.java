package com.example.gateway.messagin.service;

import com.example.client.model.ClientData;
import com.example.gateway.messagin.model.ClientRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.ObjectMessage;
import jakarta.jms.TextMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientMessageProducer {

    private final JmsTemplate jmsTemplate;

    @Value("${spring.jms.template.default-destination}")
    private String jmsDestination;

    public void sendMessage(ClientData clientData) {
        jmsTemplate.convertAndSend(jmsDestination, clientData, message -> {
            message.setStringProperty("await", "populateBasicClientInfo");
            return message;
        });
        log.info("Sent message to '{}': {}", jmsDestination, clientData);
    }
}
