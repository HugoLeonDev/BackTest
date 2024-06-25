package com.example.BackTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.BackTest.Implement.PaymentServiceImpl;
import com.example.BackTest.model.Order;
import com.example.BackTest.repository.OrderRepository;
import com.example.BackTest.service.OrderService;

@SpringBootTest
public class OrderServiceTest {
    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private PaymentServiceImpl paymentService;

    @Autowired
    private OrderService orderService;


     @Test
    void testPlaceOrder_success() {
        Order order = new Order(1, 100.0);
        when(paymentService.processPayment(order.getAmount())).thenReturn(true);
        when(orderRepository.save(order)).thenReturn(order);

        boolean result = orderService.placeOrder(order);

        assertTrue(result);
        verify(orderRepository, times(1)).save(order);
    }
    
    @Test
    void testGetOrderById_found() {
        Order order = new Order(1, 100.0);
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));

        Order result = orderService.getOrderById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(100.0, result.getAmount());
        verify(orderRepository, times(1)).findById(1);
    }

    @Test
    void testCancelOrder() {
        Order order = new Order(1, 100.0);
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));

        orderService.cancelOrder(1);

        verify(orderRepository, times(1)).findById(1);
        verify(orderRepository, times(1)).delete(order);
    }

    @Test
    void testListAllOrders() {
        Order order1 = new Order(1, 100.0);
        Order order2 = new Order(2, 200.0);
        List<Order> orders = Arrays.asList(order1, order2);
        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderService.listAllOrders();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(orders, result);
        verify(orderRepository, times(1)).findAll();
    }

}   
