package com.example.anlasmalikurumlar.repository;

import com.example.anlasmalikurumlar.model.Communication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunicationRepository extends JpaRepository<Communication, Integer> {
}
