package com.example.client_contact.controller;

import com.example.client_contact.model.ClientContacts;
import com.example.client_contact.service.ClientContactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client-contacts")
@RequiredArgsConstructor
public class ClientContactsController {

    private final ClientContactsService clientContactsService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientContacts(@PathVariable("id") String clientId,
                                               @RequestHeader(value = "sid", required = false) String sid) {
        if (sid == null || !isValidSid(sid)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Невалідний SID");
        }
        ClientContacts clientContacts = clientContactsService.getClientContacts(clientId);
        return ResponseEntity.ok(clientContacts);
    }

    private boolean isValidSid(String sid) {
        return "valid-sid".equals(sid);
    }
}