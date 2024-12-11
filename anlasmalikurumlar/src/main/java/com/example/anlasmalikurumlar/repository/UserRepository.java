package com.example.anlasmalikurumlar.repository;

import java.util.Optional;

import com.example.anlasmalikurumlar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


  User findByUsername(String username);


}
