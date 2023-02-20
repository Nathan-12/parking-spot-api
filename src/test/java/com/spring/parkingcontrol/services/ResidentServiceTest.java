package com.spring.parkingcontrol.services;

import com.spring.parkingcontrol.models.ResidentModel;
import com.spring.parkingcontrol.repositories.ResidentRepository;
import com.spring.parkingcontrol.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ResidentServiceTest {

    public static final UUID ID = java.util.UUID.fromString("db21813a-aff8-11ed-afa1-0242ac120002");
    public static final String NAME_RESIDENT = "Nathan";
    public static final String PHONE_NUMBER = "+5588981045928";
    public static final String EMAIL = "nathanlima.b@gmail.com";
    public static final int INDEX = 0;
    public static final String NOT_FOUND = "Resident not found";
    @InjectMocks
    private ResidentService residentService;
    @Mock
    private ResidentRepository residentRepository;

    private ResidentModel residentModel;
    private Optional<ResidentModel> optionalResidentModel;
    private List<ResidentModel> residentModels;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        starterResidentModel();
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(residentRepository.save(any())).thenReturn(residentModel);

        ResidentModel response = residentService.save(residentModel);

        assertNotNull(response);
        assertEquals(ResidentModel.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME_RESIDENT, response.getNameResident());
        assertEquals(PHONE_NUMBER, response.getPhoneNumber());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindAllThenReturnAnListOfResidents() {
        when(residentRepository.findAll()).thenReturn(List.of(residentModel));

        List<ResidentModel> response = residentService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(ResidentModel.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME_RESIDENT, response.get(INDEX).getNameResident());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PHONE_NUMBER, response.get(INDEX).getPhoneNumber());
    }

    @Test
    void whenFindByIdThenReturnAnResidentInstance() throws Exception {
        when(residentRepository.findById(any(UUID.class))).thenReturn(optionalResidentModel);

        ResidentModel response = residentService.findResidentObjectById(ID);

        assertNotNull(response);

        assertEquals(ResidentModel.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME_RESIDENT, response.getNameResident());
        assertEquals(PHONE_NUMBER, response.getPhoneNumber());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnObjectExceptionNotFound() {
        when(residentRepository.findById(any(UUID.class))).thenThrow(new ObjectNotFoundException(NOT_FOUND));

        try {
            residentService.findResidentObjectById(ID);
        } catch (Exception e) {
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals(NOT_FOUND, e.getMessage());
        }
    }

    @Test
    void whenFindByIdThenReturnAnOptionalResidentInstance() {
        when(residentRepository.findById(any(UUID.class))).thenReturn(optionalResidentModel);

        Optional<ResidentModel> response = residentService.findResidentById(ID);

        assertNotNull(response);
        assertEquals(ResidentModel.class, response.get().getClass());
        assertEquals(ID, response.get().getId());
    }

    @Test
    void deleteWithSuccess() {
        doNothing().when(residentRepository).delete(residentModel);
        residentService.delete(residentModel);
        verify(residentRepository, times(1)).delete(residentModel);
    }

    private void starterResidentModel() {
        residentModel = new ResidentModel(ID, NAME_RESIDENT, PHONE_NUMBER, EMAIL);
        optionalResidentModel = Optional.of(new ResidentModel(ID, NAME_RESIDENT, PHONE_NUMBER, EMAIL));
        residentModels = List.of(new ResidentModel(ID, NAME_RESIDENT, PHONE_NUMBER, EMAIL));
    }
}