package com.example.Product_APII.Repository;//package com.example.demo.Repository;

import com.example.Product_APII.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
}
