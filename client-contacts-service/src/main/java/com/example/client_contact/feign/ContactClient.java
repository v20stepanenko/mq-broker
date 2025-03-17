package com.example.client_contact.feign;

import com.example.client.wiremock.model.Contact;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "fullNameClient", url = "${app.wiremock.url}")
public interface ContactClient {

    @GetMapping("/client/{clientId}/contacts")
    List<Contact> getContacts(@PathVariable("clientId") String clientId);
}
