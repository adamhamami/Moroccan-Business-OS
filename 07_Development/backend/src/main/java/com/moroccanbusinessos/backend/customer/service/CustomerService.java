package com.moroccanbusinessos.backend.customer.service;

import com.moroccanbusinessos.backend.customer.dto.CustomerRequest;
import com.moroccanbusinessos.backend.customer.dto.CustomerResponse;
import com.moroccanbusinessos.backend.customer.entity.Customer;
import com.moroccanbusinessos.backend.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerResponse create(CustomerRequest request) {

        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Customer email already exists.");
        }

        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .company(request.getCompany())
                .email(request.getEmail())
                .phone(request.getPhone())
                .city(request.getCity())
                .country(request.getCountry())
                .build();

        customer = customerRepository.save(customer);

        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .company(customer.getCompany())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .city(customer.getCity())
                .country(customer.getCountry())
                .createdAt(customer.getCreatedAt())
                .build();
    }
}