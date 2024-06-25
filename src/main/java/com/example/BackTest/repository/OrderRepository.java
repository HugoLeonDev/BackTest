package com.example.BackTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BackTest.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
