package com.example.client_storage.db.repository;

import com.example.client_storage.db.entity.ClientEntity;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Long> {
    Slice<ClientEntity> findByClientId(String clientId);
}
