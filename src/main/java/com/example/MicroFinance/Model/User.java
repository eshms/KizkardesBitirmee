package com.example.MicroFinance.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "İsim boş olamaz")
    private String name;

    @Email(message = "Geçerli bir e-posta girin")
    private String email;
    private Object password;


    public User orElseThrow(Object userNotFound) {
        return null;
    }

    public Object getPassword() {

        return null;
    }

    public void setPassword(Object password) {
        this.password= password;

    }

    public Object getRole() {
        return null;
    }

    public boolean isPresent() {
        return false;
    }
}
