package com.example.client_cards.feign;

import com.example.client.wiremock.model.Card;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "cardsClient", url = "${app.wiremock.url}")
public interface WiremockCardsClient {

    @GetMapping("/client/{clientId}/cards")
    List<Card> getCards(@PathVariable("clientId") String clientId);
}
