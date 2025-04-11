package com.example.Product_APII.Repository;

import com.example.Product_APII.Entity.Order;
import com.example.Product_APII.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Integer userId);
    Order findByOrderCode(String orderCode);
    Order findOrderById(Long orderId);
}
