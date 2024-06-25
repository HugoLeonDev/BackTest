package com.example.BackTest.service;

import org.springframework.stereotype.Component;

@Component
public interface PaymentService {
    boolean processPayment(double amount);
}
