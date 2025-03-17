package com.example.client_info.mapper;

import com.example.client.generated.model.ClientData;
import com.example.client.wiremock.model.FullNameResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {

    @Mapping(source = "clientData.clientId", target = "clientId")
    @Mapping(source = "fullNameResponse.firstName", target = "firstName")
    @Mapping(source = "fullNameResponse.secondName", target = "secondName")
    ClientData mergeFullName(ClientData clientData, FullNameResponse fullNameResponse);
}
