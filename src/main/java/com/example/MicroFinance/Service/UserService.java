package com.example.MicroFinance.Service;

import com.example.MicroFinance.Model.User;
import com.example.MicroFinance.Repository.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import java.util.Collection;

// Service katmanı: İş kuralları burada yazılır.
// Controller'dan gelen istekleri işler ve Repository ile veritabanı etkileşimini yönetir.
@Service
public class UserService  {

    private final UserRepository userRepository;

    // Constructor-based dependency injection
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    // Yeni kullanıcı ekleme
    public User addUser(User user) {
        if (userRepository.getUsersByEmail(user.getEmail())){
            throw new ValidationException("Bu email ile kayıtlı kullanıcı bulunmaktadır.");
        }
        User savedUser = userRepository.save(user);// Kullanıcı veritabanına kaydedilir
        return savedUser;
    }

    // Tüm kullanıcıları veritabanından çekip döner
    // Tüm kullanıcıları listeleme
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ID ile kullanıcı bulma
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    // Kayıt işlemi (register): kullanıcıyı veritabanına kaydeder
    public User register(User user) {
        this.userRepository.save(user);
        return user;
    }
}