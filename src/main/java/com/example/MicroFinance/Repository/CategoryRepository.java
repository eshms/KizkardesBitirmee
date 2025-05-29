package com.example.MicroFinance.Repository;


import com.example.MicroFinance.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
    Category findById(long id);

}

