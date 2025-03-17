package com.example.client_storage.mapper;

import com.example.client_storage.db.entity.ContactEntity;
import com.example.client.generated.model.Contact;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContactMapper {

    @Mapping(target = "id", ignore = true)
    ContactEntity map(Contact contact);
}
