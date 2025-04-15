package com.example.Product_APII.Repository;


import com.example.Product_APII.Entity.CartItem;
import com.example.Product_APII.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByUser(User user);
    void deleteByUser(User user);
    CartItem findByUserAndProductId(User user, int productId);
}
