package com.example.gateway.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.client.generated.model.ClientData;
import com.example.gateway.service.ClientMessageProducer;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ClientGatewayController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ClientGatewayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JmsTemplate jmsTemplate;

    @SpyBean
    private ClientMessageProducer clientMessageProducer;

    @Test
    @SneakyThrows
    public void testReceiveClient(){
        String clientId = "1";
        String requestJson = "{ \"clientId\": \"" + clientId + "\" }";
        String expectedMessage = "[Client ID: 1] Data collection has started. Awaiting further processing...";
        mockMvc.perform(post("/client-collect-info")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedMessage));

        ArgumentCaptor<ClientData> clientDataCaptor = ArgumentCaptor.forClass(ClientData.class);
        Mockito.verify(clientMessageProducer).sendMessagePopulateFullName(clientDataCaptor.capture());
        Mockito.verify(clientMessageProducer, Mockito.times(1)).sendMessagePopulateFullName(clientDataCaptor.capture());
        assertThat(clientDataCaptor.getValue().getClientId()).isEqualTo(clientId);
    }
}
