package com.example.client_info.service;

import com.example.client.wiremock.model.FullNameResponse;
import com.example.client_info.feign.FullNameClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactsService {
    private final FullNameClient fullNameClient;

    @Cacheable(value = "full_name_cache", key = "#clientId")
    public FullNameResponse getFullName(String clientId) {
        return fullNameClient.getFullName(clientId);
    }
}