package com.example.anlasmalikurumlar.repository;


import com.example.anlasmalikurumlar.model.CompanyDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyDocumentRepository extends JpaRepository<CompanyDocument, Long> {

   List<CompanyDocument> findByCompaniesId(long companies_id);


}
