package com.example.MicroFinance.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter; // Lombok kütüphanesi ile tüm getter metodlarını otomatik oluşturur
import lombok.Setter;

// Bu sınıf, "user" tablosunu temsil eden bir JPA Entity'sidir.
@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    public User() {
    }

    // Her kullanıcıya ait benzersiz ID.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    @NotBlank(message = "İsim boş olamaz")
    private String name;

    @Email(message = "Geçerli bir e-posta girin")
    private String email;

}
