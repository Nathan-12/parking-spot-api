package com.spring.parkingcontrol.models;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_RESIDENT")
public class ResidentModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String nameResident;

    @Column(nullable = false, unique = true, length = 20)
    private String phoneNumber;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @OneToMany(mappedBy = "residentModel")
    private List<ParkingSpotModel> parkingSpotModels;

    @OneToMany(mappedBy = "residentModel")
    private List<CarModel> carModels;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNameResident() {
        return nameResident;
    }

    public void setNameResident(String nameResident) {
        this.nameResident = nameResident;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ParkingSpotModel> getParkingSpotModels() {
        return parkingSpotModels;
    }

    public void setParkingSpotModels(List<ParkingSpotModel> parkingSpotModels) {
        this.parkingSpotModels = parkingSpotModels;
    }

    public List<CarModel> getCarModels() {
        return carModels;
    }

    public void setCarModels(List<CarModel> carModels) {
        this.carModels = carModels;
    }

    public ResidentModel() {

    }

    public ResidentModel(UUID id, String nameResident, String phoneNumber, String email) {
        this.id = id;
        this.nameResident = nameResident;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
