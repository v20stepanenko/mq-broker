package com.example.client_cards.service;

import com.example.client.generated.model.ClientData;
import com.example.client.generated.model.Card;
//import com.example.client_contact.mapper.ClientMapper;
//import com.example.client_contact.service.ContactService;
import com.example.client_cards.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.example.client_cards.common.Constants.AWAIT_ATTRIBUTE;
import static com.example.client_cards.common.Constants.PERSIST_CLIENT_DATA;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final JmsTemplate jmsTemplate;
    private final CardsService cardsService;
    private final ClientMapper mapper;

    @Value("${spring.jms.template.default-destination}")
    private String queueName;

    public void processClientData(ClientData clientData) {
        if (clientData == null || clientData.getClientId() == null) {
            log.error("Received null or invalid ClientData. Skipping processing.");
            return;
        }

        log.info("Processing client data for clientId: {}", clientData.getClientId());
        List<Card> cardsResponse = cardsService.getCards(clientData.getClientId());

        ClientData resultClient = mapper.mergeCards(clientData, cardsResponse);
        log.info("Merged card data for clientId: {}", clientData.getClientId());

        try {
            jmsTemplate.convertAndSend(queueName, resultClient, message -> {
                message.setStringProperty(AWAIT_ATTRIBUTE, PERSIST_CLIENT_DATA);
                return message;
            });
            log.info("Sent updated ClientData for clientId: {} with awaitStage='{}' to queue: '{}'", clientData.getClientId(), PERSIST_CLIENT_DATA, queueName);
        } catch (Exception e) {
            log.error("Failed to send ClientData for clientId: {} to queue '{}'. Error: {}", clientData.getClientId(), queueName, e.getMessage(), e);
        }
    }
}
