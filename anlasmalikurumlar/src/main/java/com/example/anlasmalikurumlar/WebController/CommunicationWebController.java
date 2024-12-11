package com.example.anlasmalikurumlar.WebController;

import com.example.anlasmalikurumlar.dto.CommunicationDTO;
import com.example.anlasmalikurumlar.model.Communication;
import com.example.anlasmalikurumlar.model.Companies;
import com.example.anlasmalikurumlar.service.CommunicationService;
import com.example.anlasmalikurumlar.service.CompaniesService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RolesAllowed("ADMIN")
@Controller
@RequestMapping("/communications")
public class CommunicationWebController {

    private final CommunicationService communicationService;
    private final CompaniesService companiesService;

    public CommunicationWebController(CommunicationService communicationService, CompaniesService companiesService) {
        this.communicationService = communicationService;
        this.companiesService = companiesService;
    }

    @GetMapping("/new")
    public String showNewCommunicationForm(Model model) {
        CommunicationDTO communicationDTO = new CommunicationDTO();
        List<Companies> companies = companiesService.getAllCompanies();
        model.addAttribute("communicationDTO", communicationDTO);
        model.addAttribute("companies", companies);
        return "new_communication";
    }

    @PostMapping("/save")
    public String saveCommunication(@ModelAttribute("communicationDTO") CommunicationDTO communicationDTO) {
        Communication communication = new Communication();
        communication.setId(communicationDTO.getId());
        communication.setPhoneNumber(communicationDTO.getPhoneNumber());
        communication.setAddress(communicationDTO.getAddress());
        Companies company = companiesService.getCompaniesById((int) communicationDTO.getCompany_id());
        communication.setCompanies(company);
        communicationService.saveCommunication(communication);
        return "redirect:/communications";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Communication communication = communicationService.getCommunicationById(id);
        CommunicationDTO communicationDTO = new CommunicationDTO();
        communicationDTO.setId(communication.getId());
        communicationDTO.setPhoneNumber(communication.getPhoneNumber());
        communicationDTO.setAddress(communication.getAddress());
        communicationDTO.setCompany_id(communication.getCompanies().getId());

        List<Companies> companies = companiesService.getAllCompanies();
        model.addAttribute("communicationDTO", communicationDTO);
        model.addAttribute("companies", companies);
        return "edit_communication";
    }

    @PostMapping("/update")
    public String updateCommunication(@ModelAttribute("communicationDTO") CommunicationDTO communicationDTO) {
        Communication communication = new Communication();
        communication.setId(communicationDTO.getId());
        communication.setPhoneNumber(communicationDTO.getPhoneNumber());
        communication.setAddress(communicationDTO.getAddress());
        Companies company = companiesService.getCompaniesById((int) communicationDTO.getCompany_id());
        communication.setCompanies(company);
        communicationService.saveCommunication(communication);
        return "redirect:/communications";
    }

    @GetMapping
    public String listCommunications(Model model) {
        List<Communication> communications = communicationService.getAllCommunications();
        model.addAttribute("communications", communications);
        return "list_communications";
    }

    @PostMapping("/delete/{id}")
    public String deleteCommunication(@PathVariable int id) {
        communicationService.deleteCommunication(id);
        return "redirect:/communications";
    }
}
