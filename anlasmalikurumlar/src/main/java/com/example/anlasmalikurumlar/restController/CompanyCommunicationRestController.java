package com.example.anlasmalikurumlar.restController;


import com.example.anlasmalikurumlar.model.Communication;
import com.example.anlasmalikurumlar.model.CompanyCommunication;
import com.example.anlasmalikurumlar.service.CommunicationService;
import com.example.anlasmalikurumlar.service.CompaniesService;
import com.example.anlasmalikurumlar.service.CompanyCommunicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company-communications")
@Tag(name = "Şirket İletişim Bilgileri")
public class CompanyCommunicationRestController {


    private final CompanyCommunicationService companyCommunicationService;
    private final CommunicationService communicationService;
    private final CompaniesService companiesService;

    public CompanyCommunicationRestController(CompanyCommunicationService companyCommunicationService, CommunicationService communicationService, CompaniesService companiesService) {
        this.companyCommunicationService = companyCommunicationService;
        this.communicationService = communicationService;
        this.companiesService = companiesService;
    }


    @GetMapping("/companycommunication/{companyId}")

    public List<CompanyCommunication> getCompanyCommunicationByCompanyId(@PathVariable int companyId) {
        //List<CompanyCommunication> companyCommunications=companyCommunicationService.getCompanyCommunicationById(companyId);
        return null;

    }


    @GetMapping("/")
    public List<CompanyCommunication> getCompanyCommunication() {
        List<CompanyCommunication> allCompanyCommunication = companyCommunicationService.getAllCompanyCommunications();
        allCompanyCommunication.forEach(companyCommunication -> {
            Communication communication = communicationService.getCommunicationById(companyCommunication.getCommunication_id());
            companyCommunication.setCommunication(communication);
        });
        return allCompanyCommunication;
    }

    @PostMapping("/")
    public CompanyCommunication saveCompanyCommunication(@RequestBody CompanyCommunication companyCommunication) {
        return companyCommunicationService.saveCompanyCommunication(companyCommunication);
    }

    @DeleteMapping("/{id}")
    public void deleteCompanyCommunication(@PathVariable long id) {
        companyCommunicationService.deleteCompanyCommunication(id);
    }

    @PutMapping("/")
    public CompanyCommunication updateCompanyCommunication(@RequestBody CompanyCommunication companyCommunication) {
        return companyCommunicationService.saveCompanyCommunication(companyCommunication);
    }

}
