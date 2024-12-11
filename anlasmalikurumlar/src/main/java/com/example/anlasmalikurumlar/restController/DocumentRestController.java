package com.example.anlasmalikurumlar.restController;


import com.example.anlasmalikurumlar.model.Document;
import com.example.anlasmalikurumlar.service.DocumentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/document")
@Tag(name = "Döküman Bilgileri")
public class DocumentRestController {

    private final DocumentService documentService;

    public DocumentRestController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/")
    public List<Document> getDocuments() {
        return documentService.findAll();
    }


    @PostMapping("/")
    public ResponseEntity<Document> addDocument(Document document) throws Exception {
        try {
            return ResponseEntity.ok(documentService.save(document));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/{id}")
    public void deleteDocument(@PathVariable long id) {
        documentService.deleteDocumentById(id);
    }

    @PutMapping("/")
    public Document updateDocument(Document document) throws Exception {
        return documentService.save(document);
    }


}
