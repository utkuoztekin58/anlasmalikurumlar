package com.example.anlasmalikurumlar.service;


import com.example.anlasmalikurumlar.model.CompanyDocument;
import com.example.anlasmalikurumlar.repository.CompanyDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyDocumentService {
    @Autowired
    private CompanyDocumentRepository companyDocument;
    @Autowired
    private CompanyDocumentRepository companyDocumentRepository;


    public List<CompanyDocument> getAllDocuments() {
        return companyDocumentRepository.findAll();
    }

    public CompanyDocument getDocumentById(long id) {
        return companyDocumentRepository.findById(id).orElse(null);
    }

    public CompanyDocument saveDocument(CompanyDocument companyDocument) {
        return companyDocumentRepository.save(companyDocument);
    }

    public void deleteDocumentById(long id) {
        companyDocumentRepository.deleteById(id);
    }


    public List<CompanyDocument> getDocumentsByCompanyId(Long companyId) {
        return companyDocumentRepository.findByCompaniesId(companyId);
    }


    public CompanyDocumentRepository getCompanyDocument() {
        return companyDocument;
    }

    public void setCompanyDocument(CompanyDocumentRepository companyDocument) {
        this.companyDocument = companyDocument;
    }

    public CompanyDocumentRepository getCompanyDocumentRepository() {
        return companyDocumentRepository;
    }

    public void setCompanyDocumentRepository(CompanyDocumentRepository companyDocumentRepository) {
        this.companyDocumentRepository = companyDocumentRepository;
    }


}
