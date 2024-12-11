package com.example.anlasmalikurumlar.service;


import com.example.anlasmalikurumlar.model.Document;
import com.example.anlasmalikurumlar.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    public Document getDocumentById(long id) {
        return documentRepository.findById(id).orElse(null);
    }

    public Document save(Document document) throws Exception {
        if (document.getName() == null || document.getName().isEmpty())
            throw new Exception("Add boş");
        return documentRepository.save(document);
    }

    public void deleteDocumentById(long id) {
        documentRepository.deleteById(id);
    }

    public DocumentRepository getDocumentRepository() {
        return documentRepository;
    }

    public void setDocumentRepository(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }
}
