package com.example.MicroFinance.Model;

import com.example.MicroFinance.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) {
        User user = new User();
        user.setName("Ali");
        user.setEmail("ali@gmail.com");
        user.setPassword("123456");

        userRepository.save(user);
    }
}
