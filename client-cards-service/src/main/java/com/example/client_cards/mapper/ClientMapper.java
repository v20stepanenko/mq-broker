package com.example.client_cards.mapper;

import com.example.client.generated.model.Card;
import com.example.client.generated.model.ClientData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {

    @Mapping(target = "cards", source = "cards")
    ClientData mergeCards(ClientData clientData, List<Card> cards);
}
