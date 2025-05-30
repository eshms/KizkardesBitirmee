package com.example.MicroFinance.Repository;


import com.example.MicroFinance.Model.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//DB İŞLEMLERİ YAPILIYOR.

public interface UserRepository extends JpaRepository<User, Long> {
       boolean getUsersByEmail(@Email(message = "Geçerli bir e-posta girin") String email);
}
