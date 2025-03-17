package com.example.client_contact.service;

import com.example.client.generated.model.ClientData;
import com.example.client.generated.model.Contact;
import com.example.client_contact.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.example.client_contact.common.Constants.AWAIT_ATTRIBUTE;
import static com.example.client_contact.common.Constants.POPULATE_CLIENT_CARDS;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final JmsTemplate jmsTemplate;
    private final ContactService contactService;
    private final ClientMapper mapper;

    @Value("${spring.jms.template.default-destination}")
    private String queueName;

    public void processClientData(ClientData clientData) {
        if (clientData == null || clientData.getClientId() == null) {
            log.error("Received null or invalid ClientData. Skipping processing.");
            return;
        }

        log.info("Processing client data for clientId: {}", clientData.getClientId());
        List<Contact> contactResponse = contactService.getContacts(clientData.getClientId());

        ClientData resultClient = mapper.mergeContacts(clientData, contactResponse);
        log.info("Merged contact data for clientId: {}", clientData.getClientId());

        try {
            String selector = AWAIT_ATTRIBUTE + "='" + POPULATE_CLIENT_CARDS + "'";
            jmsTemplate.convertAndSend(queueName, resultClient, message -> {
                message.setStringProperty(AWAIT_ATTRIBUTE, POPULATE_CLIENT_CARDS);
                return message;
            });
            log.info("Sent updated ClientData to queue '{}' with selector '{}' for clientId: {}", queueName, selector, clientData.getClientId());
        } catch (Exception e) {
            log.error("Failed to send ClientData to queue '{}' with selector '{}'. Error: {}", queueName, AWAIT_ATTRIBUTE, e.getMessage(), e);
        }
    }
}
