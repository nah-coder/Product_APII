package com.example.Product_APII.Repository;

import com.example.Product_APII.Entity.Category;
import com.example.Product_APII.Entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Page<Payment> findAll(Pageable pageable);
}
