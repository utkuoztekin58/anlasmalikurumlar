package com.example.anlasmalikurumlar.model;

import jakarta.persistence.*;


@Entity
@Table(name = "company_document")
public class CompanyDocument {

    @Id
    @GeneratedValue
    private int id;


    @Column(name = "document_id", nullable = false)
    private long document_id;


    @Column(name = "companies_id", nullable = false)
    private Long companiesId;


    @Transient
    private Document document;

    @Transient
    private Companies companies;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDocument_id() {
        return document_id;
    }

    public void setDocument_id(long document_id) {
        this.document_id = document_id;
    }

    public Long getCompaniesId() {
        return companiesId;
    }

    public void setCompaniesId(Long companies_id) {
        this.companiesId = companies_id;
    }

    public Companies getCompanies() {
        return companies;
    }


    public void setCompanies(Companies companies) {
        this.companies = companies;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
