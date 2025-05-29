package com.example.MicroFinance.Controller;

import com.example.MicroFinance.Model.User;
import com.example.MicroFinance.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

// Kullanıcı işlemleri için kontrolcü sınıfı
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // Kullanıcı kaydı
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User savedUser = userService.register(user);
        return ResponseEntity.ok(savedUser);
    }
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Tüm kullanıcıları listeleme işlemi
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Belirli bir kullanıcıyı ID ile getirme işlemi
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @Autowired
    private UserService UserService;

}