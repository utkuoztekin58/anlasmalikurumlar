package com.example.anlasmalikurumlar.restController;


import com.example.anlasmalikurumlar.model.Companies;
import com.example.anlasmalikurumlar.model.CompanyDocument;
import com.example.anlasmalikurumlar.model.Document;
import com.example.anlasmalikurumlar.service.CompaniesService;
import com.example.anlasmalikurumlar.service.CompanyDocumentService;
import com.example.anlasmalikurumlar.service.DocumentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companydocuments")
@Tag(name = "Şirket Döküman Bilgileri")
public class CompanyDocumentRestController {

    private final CompanyDocumentService companyDocumentService;
    private final CompaniesService companiesService;
    private final DocumentService documentService;

    @Autowired
    public CompanyDocumentRestController(CompanyDocumentService companyDocumentService, CompaniesService companiesService, DocumentService documentService) {
        this.companyDocumentService = companyDocumentService;
        this.companiesService = companiesService;
        this.documentService = documentService;
    }

    @GetMapping("/")
    public List<CompanyDocument> getCompanyDocuments() {
        List<CompanyDocument> allCompanyDocuments = companyDocumentService.getAllDocuments();
        allCompanyDocuments.forEach(companyDocument -> {
            Companies companies = companiesService.getCompaniesById(companyDocument.getCompaniesId());
            companyDocument.setCompanies(companies);
        });

        return allCompanyDocuments;
    }

    @PostMapping("/")
    public CompanyDocument addCompanyDocument(@RequestBody CompanyDocument companyDocument) {
        return companyDocumentService.saveDocument(companyDocument);
    }

    @DeleteMapping("/{id}")
    public void deleteCompanyDocument(@PathVariable long id) {
        companyDocumentService.deleteDocumentById(id);

    }


    @GetMapping("/companydocument/{companyId}")
    public List<CompanyDocument> getDocumentsByCompanyId(@PathVariable long companyId) {
        List<CompanyDocument> companyDocuments = companyDocumentService.getDocumentsByCompanyId(companyId);
        companyDocuments.forEach(companyDocument -> {
            Companies companies = companiesService.getCompaniesById(companyDocument.getCompaniesId());
            companyDocument.setCompanies(companies);
            Document document = documentService.getDocumentById(companyDocument.getDocument_id());
            companyDocument.setDocument(document);

        });
        return companyDocuments;
    }


    @PutMapping("/")
    public CompanyDocument updateCompanyDocument(@RequestBody CompanyDocument companyDocument) {
        return companyDocumentService.saveDocument(companyDocument);
    }


}




