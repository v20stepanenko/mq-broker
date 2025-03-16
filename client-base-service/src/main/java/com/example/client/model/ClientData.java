
package com.example.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientData {
    private String clientId;
    private String fullName;       // Базова інформація (ПІБ)
    private String address;        // Адреса проживання
    private List<String> contacts; // Список контактів (електронна пошта, телефон і т.д.)
    private List<String> cardNumbers; // Номери карток, якщо потрібно
}
