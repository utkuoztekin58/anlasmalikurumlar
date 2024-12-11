package com.example.anlasmalikurumlar.repository;

import com.example.anlasmalikurumlar.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

}
