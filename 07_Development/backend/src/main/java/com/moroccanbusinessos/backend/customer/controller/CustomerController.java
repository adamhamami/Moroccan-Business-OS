package com.moroccanbusinessos.backend.customer.controller;

import com.moroccanbusinessos.backend.customer.dto.CustomerRequest;
import com.moroccanbusinessos.backend.customer.dto.CustomerResponse;
import com.moroccanbusinessos.backend.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(
            @Valid @RequestBody CustomerRequest request) {

        CustomerResponse response = customerService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}