package com.example.anlasmalikurumlar.dto;

public class CompanyDocumentDTO {

    private int id;
    private int documentId;
    private long companyId;


    private String documentName;
    private String content;
    private String file_extention;
    private String companyName;
    private String companyEmail;
    private Integer companyDiscountPercentage;
    private Long categoryId;
    private String categoryName;


    public CompanyDocumentDTO(int id, int documentId, long companyId, String documentName, String companyName, String companyEmail, Integer companyDiscountPercentage, Long categoryId, String categoryName, String content, String file_extention) {
        this.id = id;
        this.documentId = documentId;
        this.companyId = companyId;
        this.documentName = documentName;
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyDiscountPercentage = companyDiscountPercentage;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.content = content;
        this.file_extention = file_extention;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFile_extention() {
        return file_extention;
    }

    public void setFile_extention(String file_extention) {
        this.file_extention = file_extention;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public Integer getCompanyDiscountPercentage() {
        return companyDiscountPercentage;
    }

    public void setCompanyDiscountPercentage(Integer companyDiscountPercentage) {
        this.companyDiscountPercentage = companyDiscountPercentage;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;

    }
}
