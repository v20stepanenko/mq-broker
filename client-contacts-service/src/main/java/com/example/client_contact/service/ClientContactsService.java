package com.example.client_contact.service;

import com.example.client_contact.model.ClientContacts;
import com.example.client_contact.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Slf4j
@Service
public class ClientContactsService {
    public ClientContacts getClientContacts(String clientId) {
        return new ClientContacts(clientId, Arrays.asList(
            new Contact("email", "client@example.com"),
            new Contact("phone", "+380123456789")
        ));
    }
}