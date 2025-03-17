package com.example.client_info.service;

import com.example.client.generated.model.ClientData;
import com.example.client.wiremock.model.FullNameResponse;
import com.example.client_info.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import static com.example.client_info.common.Constants.AWAIT_ATTRIBUTE;
import static com.example.client_info.common.Constants.POPULATE_CLIENT_CONTACTS;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final JmsTemplate jmsTemplate;
    private final ContactsService fullNameService;
    private final ClientMapper mapper;

    @Value("${spring.jms.template.default-destination}")
    private String queueName;

    public void processClientData(ClientData clientData) {
        if (clientData == null || clientData.getClientId() == null) {
            log.error("Received null or invalid ClientData. Skipping processing.");
            return;
        }

        log.info("Processing client data for clientId: {}", clientData.getClientId());
        FullNameResponse fullName = fullNameService.getFullName(clientData.getClientId());


        ClientData resultClient = mapper.mergeFullName(clientData, fullName);
        log.info("Successfully added fullName '{} {}' for clientId: {}", fullName.getFirstName(), fullName.getSecondName(), clientData.getClientId());

        try {
            jmsTemplate.convertAndSend(queueName, resultClient, message -> {
                message.setStringProperty(AWAIT_ATTRIBUTE, POPULATE_CLIENT_CONTACTS);
                return message;
            });
            log.info("Sent updated ClientData for clientId: {} with awaitStage='{}' to queue: '{}'", clientData.getClientId(), POPULATE_CLIENT_CONTACTS, queueName);
        } catch (Exception e) {
            log.error("Failed to send ClientData for clientId: {} to queue '{}'. Error: {}", clientData.getClientId(), queueName, e.getMessage(), e);
        }
    }
}
