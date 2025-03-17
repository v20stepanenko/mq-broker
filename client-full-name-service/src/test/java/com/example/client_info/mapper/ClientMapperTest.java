package com.example.client_info.mapper;

import com.example.client.generated.model.ClientData;
import com.example.client.wiremock.model.FullNameResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClientMapperTest {
    private ClientMapper clientMapper;

    @BeforeEach
    void setUp() {
        clientMapper = Mappers.getMapper(ClientMapper.class);
    }

    @Test
    void shouldMergeFullNameIntoClientData() {
        // Given
        ClientData clientData = new ClientData();
        clientData.setClientId("12345");

        FullNameResponse fullNameResponse = new FullNameResponse();
        fullNameResponse.setFirstName("John");
        fullNameResponse.setSecondName("Doe");

        // When
        ClientData mergedClientData = clientMapper.mergeFullName(clientData, fullNameResponse);

        // Then
        assertNotNull(mergedClientData);
        assertEquals("12345", mergedClientData.getClientId());
        assertEquals("John", mergedClientData.getFirstName());
        assertEquals("Doe", mergedClientData.getSecondName());
    }
}