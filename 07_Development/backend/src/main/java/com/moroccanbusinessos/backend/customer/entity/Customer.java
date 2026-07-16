package com.moroccanbusinessos.backend.customer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String company;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    private String city;

    private String country;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

}