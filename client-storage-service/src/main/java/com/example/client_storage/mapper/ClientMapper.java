package com.example.client_storage.mapper;

import com.example.client_storage.db.entity.ClientEntity;
import com.example.client_storage.db.entity.CardEntity;
import com.example.client_storage.db.entity.ContactEntity;
import com.example.client.generated.model.ClientData;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {CardMapper.class, ContactMapper.class})
public interface ClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "cards", source = "cards")
    @Mapping(target = "contacts", source = "contacts")
    @BeanMapping(builder = @Builder(disableBuilder = true))
    ClientEntity map(ClientData clientData);

    @AfterMapping
    default void setClientToNestedEntity(@MappingTarget ClientEntity clientEntity) {
        if (clientEntity.getCards() != null) {
            clientEntity.getCards().forEach(cardEntity -> cardEntity.setClient(clientEntity));
        }
        if (clientEntity.getContacts() != null) {
            clientEntity.getContacts().forEach(contactEntity -> contactEntity.setClient(clientEntity));
        }
    }
}

