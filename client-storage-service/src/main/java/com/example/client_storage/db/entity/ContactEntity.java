package com.example.client_storage.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import java.time.LocalDateTime;

@Entity
@Table(name = "contact")
@SoftDelete
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Contact type cannot be null")
    @Enumerated(EnumType.STRING)
    private ContactType type;

    @NotBlank(message = "Contact value cannot be blank")
    @Column(name = "contact_value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
