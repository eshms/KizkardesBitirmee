package com.example.MicroFinance.Repository;


import com.example.MicroFinance.Model.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);

    boolean existsByEmail(@Email(message = "Geçerli bir e-posta girin") String email);

    Optional<User> findByEmailAndPassword(String email, String password);
}
