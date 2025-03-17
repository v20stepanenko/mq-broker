package com.example.client_contact.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.example.client.generated.model.ClientData;
import com.example.client.generated.model.Contact;
import com.example.client.wiremock.model.FullNameResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

class ContactMapperTest {

    private ClientMapper clientMapper;

    @BeforeEach
    void setUp() {
        clientMapper = Mappers.getMapper(ClientMapper.class);
    }

    @Test
    void shouldMergeContactsIntoClientData() {
        // Given
        ClientData clientData = new ClientData();
        clientData.setClientId("12345");
        clientData.setFirstName("John");
        clientData.setSecondName("Doe");

        List<Contact> contacts = List.of(
                new Contact().type(Contact.TypeEnum.EMAIL).value("john.doe@example.com"),
                new Contact().type(Contact.TypeEnum.PHONE).value("+123456789")
        );

        assertEquals(0, clientData.getContacts().size());

        // When
        ClientData mergedClientData = clientMapper.mergeContacts(clientData, contacts);

        // Then
        assertNotNull(mergedClientData);
        assertEquals("12345", mergedClientData.getClientId());
        assertEquals("John", mergedClientData.getFirstName());
        assertEquals("Doe", mergedClientData.getSecondName());
        assertNotNull(mergedClientData.getContacts());
        assertEquals(2, mergedClientData.getContacts().size());
    }
}
