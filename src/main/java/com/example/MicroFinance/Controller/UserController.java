package com.example.MicroFinance.Controller;

import com.example.MicroFinance.Model.User;
import com.example.MicroFinance.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;


//CONTROLLER WEB SERVİCE İŞLEMLERİ
// Kullanıcı işlemleri için kontrolcü sınıfı
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // Kullanıcı kaydı
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody @Valid User user) {
        User savedUser = userService.register(user);
        return ResponseEntity.ok(savedUser);
    }
    @Autowired // Spring, UserRepository'nin bir nesnesini buraya otomatik olarak yerleştirir
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Tüm kullanıcıları listeleme işlemi
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    //@GetMapping, Spring Boot'ta bir HTTP GET isteğini bir metoda bağlamak için kullanılır.
    // Belirli bir kullanıcıyı ID ile getirme işlemi
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    // PATH VARİABLE URL’nin içindeki değişkeni almak için
    @Autowired
    private UserService UserService;

}