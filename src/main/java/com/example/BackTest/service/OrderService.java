package com.example.BackTest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.BackTest.Implement.PaymentServiceImpl;
import com.example.BackTest.model.Order;
import com.example.BackTest.repository.OrderRepository;

@Service
public class OrderService {

    
    private final OrderRepository orderRepository;
    private final PaymentServiceImpl paymentService;
    
    
    public OrderService(OrderRepository orderRepository, PaymentServiceImpl paymentService) {
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
    }
    

    public boolean placeOrder(Order order) {
        boolean paymentProcessed = paymentService.processPayment(order.getAmount());
        if (paymentProcessed) {
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    public Order getOrderById(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void cancelOrder(int id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        orderRepository.delete(order);
    }

    public List<Order> listAllOrders() {
        return orderRepository.findAll();
    }

    public void deleteOrderById(int id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Order not found");
        }
    }

    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }

}
