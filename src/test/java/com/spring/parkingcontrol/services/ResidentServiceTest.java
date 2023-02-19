package com.spring.parkingcontrol.services;

import com.spring.parkingcontrol.models.ResidentModel;
import com.spring.parkingcontrol.repositories.ResidentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ResidentServiceTest {

    public static final UUID ID = java.util.UUID.fromString("db21813a-aff8-11ed-afa1-0242ac120002");
    public static final String NAME_RESIDENT = "Nathan";
    public static final String PHONE_NUMBER = "+5588981045928";
    public static final String EMAIL = "nathanlima.b@gmail.com";
    @InjectMocks
    private ResidentService residentService;
    @Mock
    private ResidentRepository residentRepository;

    private ResidentModel residentModel;
    private Optional<ResidentModel> optionalResidentModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        starterResidentModel();
    }

    @Test
    void save() {
    }

    @Test
    void findAll() {
    }

    @Test
    void whenFindByIdThenReturnAnResidentInstance() {
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
    void delete() {
    }

    private void starterResidentModel() {
        residentModel = new ResidentModel(ID, NAME_RESIDENT, PHONE_NUMBER, EMAIL);
        optionalResidentModel = Optional.of(new ResidentModel(ID, NAME_RESIDENT, PHONE_NUMBER, EMAIL));
    }
}