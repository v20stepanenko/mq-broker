package com.example.client_info.feign;

import com.example.client.wiremock.model.FullNameResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "contactsClient", url = "${app.wiremock.url}")
public interface FullNameClient {

    @GetMapping(value = "/client/{clientId}/full-name", produces = "application/json")
    FullNameResponse getFullName(@PathVariable("clientId") String clientId);
}
