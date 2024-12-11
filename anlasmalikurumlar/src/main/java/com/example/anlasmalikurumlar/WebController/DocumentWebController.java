package com.example.anlasmalikurumlar.WebController;

import com.example.anlasmalikurumlar.model.Document;
import com.example.anlasmalikurumlar.service.DocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/documents")
public class DocumentWebController {

    private final DocumentService documentService;

    public DocumentWebController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping
    public String listDocuments(Model model) {
        List<Document> documents = documentService.findAll();
        model.addAttribute("documents", documents);
        return "list_documents";
    }

    @GetMapping("/new")
    public String newDocumentForm(Model model) {
        model.addAttribute("document", new Document());
        return "new_document";
    }

    @PostMapping("/save")
    public String saveDocument(@ModelAttribute Document document) {
        try {
            documentService.save(document);
            return "redirect:/documents";
        } catch (Exception e) {
            return "redirect:/documents/new?error=" + e.getMessage();
        }
    }

    @GetMapping("/edit/{id}")
    public String editDocumentForm(@PathVariable long id, Model model) {
        Document document = documentService.getDocumentById(id);
        if (document == null) {
            return "redirect:/documents/list?error=Document not found";
        }
        model.addAttribute("document", document);
        return "edit_document";
    }

    @PostMapping("/update")
    public String updateDocument(@ModelAttribute Document document) {
        try {
            documentService.save(document);
            return "redirect:/documents";
        } catch (Exception e) {
            return "redirect:/documents/edit/" + document.getId() + "?error=" + e.getMessage();
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteDocument(@PathVariable long id) {
        documentService.deleteDocumentById(id);
        return "redirect:/documents";
    }
}
