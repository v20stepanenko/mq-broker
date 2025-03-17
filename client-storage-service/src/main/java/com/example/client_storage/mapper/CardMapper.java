package com.example.client_storage.mapper;

import com.example.client_storage.db.entity.CardEntity;
import com.example.client.generated.model.Card;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardMapper {

    @Mapping(target = "id", ignore = true)
    CardEntity map(Card card);
}
