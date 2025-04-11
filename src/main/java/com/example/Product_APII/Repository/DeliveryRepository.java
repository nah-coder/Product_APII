package com.example.Product_APII.Repository;

import com.example.Product_APII.Entity.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery,Integer> {
    Page<Delivery> findAll(Pageable pageable);
    List<Delivery> findByDeliveryNameContainingIgnoreCase(String name);

}
