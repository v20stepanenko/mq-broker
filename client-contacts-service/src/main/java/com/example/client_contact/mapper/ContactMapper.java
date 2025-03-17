package com.example.client_contact.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContactMapper {

    List<com.example.client.generated.model.Contact> map(List<com.example.client.wiremock.model.Contact> contacts);

    com.example.client.generated.model.Contact map(com.example.client.wiremock.model.Contact contact);
}
