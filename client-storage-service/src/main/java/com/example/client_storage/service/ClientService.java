package com.example.client_storage.service;

import com.example.client.generated.model.ClientData;
import com.example.client_storage.db.entity.ClientEntity;
import com.example.client_storage.db.repository.ClientRepository;
import com.example.client_storage.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper mapper;

    @Transactional
    public void saveClientData(ClientData clientData) {
        log.info("Starting persistence for clientId: {}", clientData.getClientId());

        try {
            ClientEntity clientEntity = mapper.map(clientData);
            clientRepository.save(clientEntity);
            log.info("Successfully persisted ClientData: {}", clientData);
        } catch (Exception e) {
            log.error("Failed to persist ClientData for clientId: {}. Error: {}",
                      clientData.getClientId(), e.getMessage(), e);
            throw e;
        }
    }
}
