package com.example.client_storage.db.repository;

import com.example.client_storage.db.entity.CardEntity;
import com.example.client_storage.db.entity.ClientEntity;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<CardEntity, Long> {

    @Query("SELECT c FROM CardEntity c WHERE c.client.clientId = :clientId")
    Slice<CardEntity> findByClientId(@Param("clientId") String clientId);
}
