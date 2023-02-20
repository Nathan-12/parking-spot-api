package com.spring.parkingcontrol.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.parkingcontrol.dtos.ResidentDto;
import com.spring.parkingcontrol.models.ResidentModel;
import com.spring.parkingcontrol.services.ResidentService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/resident")
public class ResidentController {
	
	final ResidentService residentService;
	
	public ResidentController(ResidentService residentService) {
		this.residentService = residentService;
	}
	
	@PostMapping
	public ResponseEntity<Object> saveResident(@RequestBody @Valid ResidentDto residentDto) {
		var residentModel = new ResidentModel();
		BeanUtils.copyProperties(residentDto, residentModel);
		return ResponseEntity.status(HttpStatus.OK).body(residentService.save(residentModel));
	}
	
	@GetMapping
    public ResponseEntity<List<ResidentModel>> getAllResidents() {
        return ResponseEntity.status(HttpStatus.OK).body(residentService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getOneResident(@PathVariable(value = "id") UUID id) {
        Optional<ResidentModel> residentModelOptional = residentService.findResidentById(id);
        if (!residentModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resident not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(residentModelOptional.get());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteResident(@PathVariable(value = "id") UUID id) {
        Optional<ResidentModel> residentModelOptional = residentService.findResidentById(id);
        if (!residentModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resident not found.");
        }
        residentService.delete(residentModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Resident deleted successfully.");
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateResident(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid ResidentDto residentDto) {
        Optional<ResidentModel> residentModelOptional = residentService.findResidentById(id);
        if (!residentModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resident not found.");
        }
        var residentModel = new ResidentModel();
        BeanUtils.copyProperties(residentDto, residentModel);
        residentModel.setId(residentModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(residentService.save(residentModel));
    }

}
