package com.example.anlasmalikurumlar.repository;


import com.example.anlasmalikurumlar.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {






}
