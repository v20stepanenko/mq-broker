package com.example.client_storage.mapper;

import com.example.client.generated.model.Card;
import com.example.client.generated.model.ClientData;
import com.example.client.generated.model.Contact;
import com.example.client_storage.db.entity.ClientEntity;
import com.example.client_storage.mapper.config.MapperTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MapperTestConfig.class})
class ClientMapperTest {

    @Autowired
    private ClientMapper clientMapper;

    @Test
    void shouldMapClientDataToClientEntity() {
        // Given
        ClientData clientData = new ClientData();
        clientData.setClientId("12345");
        clientData.setFirstName("John");
        clientData.setSecondName("Doe");

        Card card = new Card();
        card.setCardNumber("1111222233334444");
        card.setExpiryDate(LocalDate.of(2026, 12, 31));
        card.setCardType(com.example.client.generated.model.Card.CardTypeEnum.VISA);

        Contact contact = new Contact();
        contact.setType(com.example.client.generated.model.Contact.TypeEnum.EMAIL);
        contact.setValue("john.doe@example.com");

        clientData.setCards(List.of(card));
        clientData.setContacts(List.of(contact));

        // When
        ClientEntity clientEntity = clientMapper.map(clientData);

        // Then
        assertNotNull(clientEntity);
        assertEquals("12345", clientEntity.getClientId());
        assertEquals("John", clientEntity.getFirstName());
        assertEquals("Doe", clientEntity.getSecondName());

        assertNotNull(clientEntity.getCards());
        assertEquals(1, clientEntity.getCards().size());
        assertEquals("1111222233334444", clientEntity.getCards().get(0).getCardNumber());
        assertEquals(LocalDate.of(2026, 12, 31), clientEntity.getCards().get(0).getExpiryDate());

        assertNotNull(clientEntity.getContacts());
        assertEquals(1, clientEntity.getContacts().size());
        assertEquals("john.doe@example.com", clientEntity.getContacts().get(0).getValue());

        assertEquals(clientEntity, clientEntity.getCards().get(0).getClient());
        assertEquals(clientEntity, clientEntity.getContacts().get(0).getClient());
    }
}
