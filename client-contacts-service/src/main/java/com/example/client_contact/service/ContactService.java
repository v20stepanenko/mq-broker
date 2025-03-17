package com.example.client_contact.service;

import com.example.client.generated.model.Contact;
import com.example.client_contact.feign.ContactClient;
import com.example.client_contact.mapper.ContactMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactService {
    private final ContactClient contactClient;
    private final ContactMapper mapper;

    @Cacheable(value = "client_contacts", key = "#clientId")
    public List<Contact> getContacts(String clientId) {
        return mapper.map(contactClient.getContacts(clientId));
    }
}