package com.example.anlasmalikurumlar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1, max = 255, message = "Uzunluk 1 ile 255 arasında olmalıdır")
    @Column(name = "document_name", nullable = false, unique = true)
    private String name;


    @Column(name = "documents_content", nullable = false, unique = true)
    private String content;


    @Column(name = "file_extention", nullable = false, unique = true)
    private String file_extention;


    public int getId() {
        return (int) id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
