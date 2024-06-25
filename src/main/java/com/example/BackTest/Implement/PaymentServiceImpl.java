package com.example.BackTest.Implement;

import org.springframework.stereotype.Service;

import com.example.BackTest.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService  {

    @Override
    public boolean processPayment(double amount) {
        return true;
    }

}
