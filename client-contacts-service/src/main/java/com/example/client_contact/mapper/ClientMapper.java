package com.example.client_contact.mapper;

import com.example.client.generated.model.ClientData;
import com.example.client.generated.model.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {

    @Mapping(target = "contacts", source = "contacts")
    ClientData mergeContacts(ClientData clientData, List<Contact> contacts);
}
