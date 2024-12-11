package com.example.anlasmalikurumlar.restController;


import com.example.anlasmalikurumlar.model.Communication;
import com.example.anlasmalikurumlar.model.Companies;
import com.example.anlasmalikurumlar.service.CommunicationService;
import com.example.anlasmalikurumlar.service.CompaniesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/communication")
@Tag(name = " İletişim Bilgileri")
public class CommunicationRestController {


    private final CommunicationService communicationService;
    private final CompaniesService companiesService;

    public CommunicationRestController(CommunicationService communicationService, CompaniesService companiesService) {
        this.communicationService = communicationService;
        this.companiesService = companiesService;
    }

    @GetMapping("/")
    public List<Communication> getCommunications() {
        List<Communication> communications = communicationService.getAllCommunications();
        communications.forEach(Communication -> {
            Companies companies = companiesService.getCompaniesById(Communication.getCompanies().getId());//BURAYA SONRA BAK
            Communication.setCompanies(companies);
        });
        return communications;
    }

    @PostMapping("/")
    public Communication AddCommunication(@RequestBody Communication communication) {
        return communicationService.saveCommunication(communication);
    }

    @DeleteMapping("/{id}")
    public void deleteCommunication(@PathVariable int id) {
        communicationService.deleteCommunication(id);
    }

    @GetMapping("/{id}")
    public Communication getCommunication(@PathVariable int id) {
        return communicationService.getCommunicationById(id);
    }

    @PutMapping("/")
    public Communication updateCommunication(@RequestBody Communication communication) {
        return communicationService.saveCommunication(communication);
    }


}










