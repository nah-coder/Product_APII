package com.example.Product_APII.Repository;

import com.example.Product_APII.Entity.Category;
import com.example.Product_APII.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Page<Product> findAll(Pageable pageable);
}
