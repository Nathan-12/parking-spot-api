package com.spring.parkingcontrol.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.parkingcontrol.models.ResidentModel;
import com.spring.parkingcontrol.repositories.ResidentRepository;

@Service
public class ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    @Transactional
    public ResidentModel save(ResidentModel residentModel) {
        return residentRepository.save(residentModel);
    }

    public List<ResidentModel> findAll() {
        return residentRepository.findAll();
    }

    public Optional<ResidentModel> findResidentById(UUID id) {
        return residentRepository.findById(id);
    }

    public ResidentModel findResidentObjectById(UUID id) {
        Optional<ResidentModel> optionalResidentModel = residentRepository.findById(id);
        return optionalResidentModel.orElseThrow();
    }

    @Transactional
    public void delete(ResidentModel residentModel) {
        residentRepository.delete(residentModel);
    }

}
