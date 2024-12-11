package com.example.anlasmalikurumlar.service;

import com.example.anlasmalikurumlar.model.CompanyCommunication;
import com.example.anlasmalikurumlar.repository.CompanyCommunicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyCommunicationService {
    @Autowired
    private CompanyCommunicationRepository companyCommunicationRepository;

    public CompanyCommunicationService(CompanyCommunicationRepository companyCommunicationRepository) {
        this.companyCommunicationRepository = companyCommunicationRepository;

    }

    public List<CompanyCommunication> getAllCompanyCommunications() {
        return companyCommunicationRepository.findAll();
    }


    public CompanyCommunication getCompanyCommunicationById(long id) {
        return companyCommunicationRepository.getById(id);

    }

    public CompanyCommunication saveCompanyCommunication(CompanyCommunication companyCommunication) {
        return companyCommunicationRepository.save(companyCommunication);
    }


    public void deleteCompanyCommunication(long id) {
        companyCommunicationRepository.deleteById(id);
    }


}
