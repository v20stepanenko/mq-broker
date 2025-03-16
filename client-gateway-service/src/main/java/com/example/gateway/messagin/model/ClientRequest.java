package com.example.gateway.messagin.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class ClientRequest implements Serializable {
    private String clientId;
}
