package com.example.client_cards.service;

import com.example.client_cards.feign.WiremockCardsClient;
import com.example.client.generated.model.Card;
import com.example.client_cards.mapper.CardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardsService {
    private final WiremockCardsClient cardsClient;
    private final CardMapper mapper;

    @Cacheable(value = "client_cards", key = "#clientId")
    public List<Card> getCards(String clientId) {
        return mapper.map(cardsClient.getCards(clientId));
    }
}