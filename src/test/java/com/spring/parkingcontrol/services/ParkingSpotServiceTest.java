package com.spring.parkingcontrol.services;

import com.spring.parkingcontrol.models.ParkingSpotModel;
import com.spring.parkingcontrol.repositories.ParkingSpotRepository;
import com.spring.parkingcontrol.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ParkingSpotServiceTest {

    public static final UUID ID = UUID.fromString("db21813a-aff8-11ed-afa1-0242ac120003");
    public static final String SPOT_NUMBER = "100";
    public static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now(ZoneId.of("UTC"));
    public static final String RESPONSIBLE_NAME = "Nathan";
    public static final String APARTMENT = "12";
    public static final String BLOCK = "N";
    @InjectMocks
    private ParkingSpotService parkingSpotService;

    @Mock
    private ParkingSpotRepository parkingSpotRepository;

    private ParkingSpotModel parkingSpotModel;
    private Optional<ParkingSpotModel> optionalParkingSpotModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startParkingSpotModel();
    }

    @Test
    public void whenSaveParkingSpotThenReturnParkinkSpotModel() {
        Mockito.when(parkingSpotRepository.save(Mockito.any())).thenReturn(parkingSpotModel);

        ParkingSpotModel response = parkingSpotService.save(parkingSpotModel);

        assertNotNull(response);
        assertEquals(ParkingSpotModel.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(SPOT_NUMBER, response.getParkingSpotNumber());
    }

    @Test
    public void shouldReturnTrueIfExistesParkingSpot() {
        Mockito.when(parkingSpotRepository.existsByParkingSpotNumber(Mockito.any())).thenReturn(true);

        boolean response = parkingSpotService.existsByParkingSpotNumber(SPOT_NUMBER);

        Assertions.assertTrue(response);
    }

    @Test
    public void whenFindParkingByIdThenReturnParkingSpotModelInstance() {
        Mockito.when(parkingSpotRepository.findById(Mockito.any(UUID.class))).thenReturn(optionalParkingSpotModel);

        Optional<ParkingSpotModel> response = parkingSpotService.findById(ID);

        assertNotNull(response);
        assertEquals(ParkingSpotModel.class, response.get().getClass());
        assertEquals(ID, response.get().getId());
    }

    @Test
    public void whenErrorWasDetectedThenReturnAObjectException() {
        Mockito.when(parkingSpotRepository.findById(Mockito.any(UUID.class))).thenThrow(new ObjectNotFoundException("Parking Spot not found"));
        try {
            parkingSpotService.findByIdParkingSpot(ID);
        } catch (Exception e) {
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals("Parking Spot not found", e.getMessage());
        }
    }

    @Test
    public void deleteParkingSpotWithSuccess() {
        Mockito.doNothing().when(parkingSpotRepository).delete(parkingSpotModel);
        parkingSpotService.delete(parkingSpotModel);
        Mockito.verify(parkingSpotRepository, Mockito.times(1)).delete(parkingSpotModel);
    }

    private void startParkingSpotModel() {
        parkingSpotModel = new ParkingSpotModel(ID, SPOT_NUMBER, LOCAL_DATE_TIME, RESPONSIBLE_NAME, APARTMENT, BLOCK);
        optionalParkingSpotModel = Optional.of(new ParkingSpotModel(ID, SPOT_NUMBER, LOCAL_DATE_TIME, RESPONSIBLE_NAME, APARTMENT, BLOCK));
    }
}