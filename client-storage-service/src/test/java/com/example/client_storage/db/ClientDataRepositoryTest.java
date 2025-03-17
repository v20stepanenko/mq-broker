package com.example.client_storage.db;

import static org.junit.jupiter.api.Assertions.*;

import com.example.client_storage.db.entity.CardEntity;
import com.example.client_storage.db.entity.CardType;
import com.example.client_storage.db.entity.ClientEntity;
import com.example.client_storage.db.repository.CardRepository;
import com.example.client_storage.db.repository.ClientRepository;
import com.example.client_storage.db.config.JpaTestConfig;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import jakarta.validation.ConstraintViolationException;
import org.springframework.test.context.ContextConfiguration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@DataJpaTest
@ContextConfiguration(classes = {JpaTestConfig.class})
@ActiveProfiles("test-h2")
class ClientDataRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;

    @Test
    void shouldSaveAndRetrieveClientData() {
        // Given
        String clientId = "12345";
        String firstName = "John";
        String secondName = "Doe";
        ClientEntity client = ClientEntity.builder()
                                      .clientId(clientId)
                                      .firstName(firstName)
                                      .secondName(secondName)
                                      .build();
        clientRepository.save(client);

        // When
        ClientEntity retrievedClient = clientRepository.findByClientId(clientId).getContent().get(0);

        // Then
        assertNotNull(retrievedClient);
        assertEquals(clientId, retrievedClient.getClientId());
        assertEquals(firstName, retrievedClient.getFirstName());
        assertEquals(secondName, retrievedClient.getSecondName());
    }

    @Test
    void shouldThrowValidationExceptionForMissingFirstName() {
        ClientEntity client = ClientEntity.builder()
                                      .clientId("12345")
                                      .secondName("Doe")
                                      .build();
        assertThrows(ConstraintViolationException.class, () -> clientRepository.save(client));
    }

    @Test
    void shouldPerformSoftDeleteOnClient() {
        // Given
        String clientId = "12345";
        String firstName = "John";
        String secondName = "Doe";
        ClientEntity client = ClientEntity.builder()
                                      .clientId(clientId)
                                      .firstName(firstName)
                                      .secondName(secondName)
                                      .build();
        clientRepository.save(client);
        ClientEntity retrievedClient = clientRepository.findByClientId(clientId).getContent().get(0);
        assertEquals(clientRepository.findByClientId(clientId).getContent().size(), 1);
        Long id = retrievedClient.getId();

        // When
        clientRepository.delete(retrievedClient);
        entityManager.flush();
        entityManager.clear();
        Optional<ClientEntity> deletedClient = clientRepository.findById(id);
        Map<String, Object> deletedClientProperties = jdbcTemplate.queryForMap("SELECT * FROM client WHERE id = ?", id);

        // Then
        assertFalse(deletedClient.isPresent());
        assertEquals(true, deletedClientProperties.get("deleted"));
    }

    @Test
    void shouldCascadeSaveCardsAndContacts() {
        // Given
        String clientId = "67890";
        String firstName = "Alice";
        String secondName = "Smith";
        ClientEntity client = ClientEntity.builder()
                                      .clientId(clientId)
                                      .firstName(firstName)
                                      .secondName(secondName)
                                      .build();

        CardEntity card = CardEntity.builder()
                                  .cardNumber("1111222233334444")
                                  .expiryDate(LocalDate.of(2026, 12, 31))
                                  .cardType(CardType.VISA)
                                  .client(client)
                                  .build();


        client.setCards(List.of(card));

        // When
        clientRepository.save(client);
        entityManager.flush();
        entityManager.clear();

        // Then
        ClientEntity retrievedClient = clientRepository.findByClientId(clientId).getContent().get(0);
        CardEntity retrievedCards = cardRepository.findByClientId(clientId).getContent().get(0);

        assertNotNull(retrievedClient);
        assertEquals(1, retrievedClient.getCards().size());
        assertEquals(retrievedCards.getCardNumber(), card.getCardNumber());
        assertEquals(retrievedCards.getCardType(), card.getCardType());
    }
}
