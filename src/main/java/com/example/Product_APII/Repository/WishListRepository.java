package com.example.Product_APII.Repository;

import com.example.Product_APII.Entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {
    Optional<WishList> findByUserId(Integer userId);
    List<WishList> findAllByUserId(Integer userId);
}
