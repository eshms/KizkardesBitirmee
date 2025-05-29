package com.example.MicroFinance.Service;

import com.example.MicroFinance.Model.User;
import com.example.MicroFinance.Repository.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import java.util.Collection;


@Service
public class UserService  {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    // Yeni kullanıcı ekleme
    public User addUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())){
            throw new ValidationException("Bu email ile kayıtlı kullanıcı bulunmaktadır.");
        }
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    // Tüm kullanıcıları listeleme
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ID ile kullanıcı bulma
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User register(User user) {
        return userRepository.save(user);
    }
}