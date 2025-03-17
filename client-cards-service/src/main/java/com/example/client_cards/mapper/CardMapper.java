package com.example.client_cards.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardMapper {
    List<com.example.client.generated.model.Card> map(List<com.example.client.wiremock.model.Card> cards);

    com.example.client.generated.model.Card map(com.example.client.wiremock.model.Card card);
}
