

package com.example.anlasmalikurumlar.service;

import com.example.anlasmalikurumlar.model.Communication;
import com.example.anlasmalikurumlar.repository.CommunicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunicationService {

    private CommunicationRepository communicationRepository;

    public CommunicationService(CommunicationRepository communicationRepository) {
        this.communicationRepository = communicationRepository;
    }


    public List<Communication> getAllCommunications() {
        return communicationRepository.findAll();
    }


    public Communication getCommunicationById(int id) {
        return communicationRepository.findById(id).orElseThrow();
    }


    public Communication saveCommunication(Communication communication) {
        return communicationRepository.save(communication);

    }


    public void deleteCommunication(int id) {
        communicationRepository.deleteById(id);
    }


    public CommunicationRepository getCommunicationRepository() {
        return communicationRepository;
    }

    public void setCommunicationRepository(CommunicationRepository communicationRepository) {
        this.communicationRepository = communicationRepository;
    }
}




