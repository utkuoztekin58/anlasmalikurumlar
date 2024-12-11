package com.example.anlasmalikurumlar.model;


import jakarta.persistence.*;

@Entity
@Table(name = "company_communication")
public class CompanyCommunication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "communication_id")
    private int communication_id;


    @Transient
    private Communication communication;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCommunication_id() {
        return communication_id;
    }

    public void setCommunication_id(int communication_id) {
        this.communication_id = communication_id;
    }

    public Communication getCommunication() {
        return communication;
    }

    public void setCommunication(Communication communication) {
        this.communication = communication;
    }
}
