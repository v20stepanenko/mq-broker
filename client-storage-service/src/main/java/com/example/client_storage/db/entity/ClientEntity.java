package com.example.client_storage.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "client")
@SoftDelete
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Client ID cannot be blank")
    private String clientId;

    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Second name cannot be blank")
    private String secondName;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<CardEntity> cards;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<ContactEntity> contacts;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
