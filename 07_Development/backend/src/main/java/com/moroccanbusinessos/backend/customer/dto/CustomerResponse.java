package com.moroccanbusinessos.backend.customer.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String company;

    private String email;

    private String phone;

    private String city;

    private String country;

    private LocalDateTime createdAt;

}