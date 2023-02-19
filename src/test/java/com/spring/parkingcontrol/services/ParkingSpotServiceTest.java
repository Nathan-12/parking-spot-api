package com.spring.parkingcontrol.services;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.parkingcontrol.models.ParkingSpotModel;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class ParkingSpotServiceTest {
	
	@Autowired
	private ParkingSpotService parkingSpotService;
	
	@Test
	public void ShouldSearchParkingSpotById() {
		Optional<ParkingSpotModel> parkingSpot = parkingSpotService.findById(UUID.fromString("88200400-21b5-40d0-9fd2-7bc25820f18f"));
		assertEquals("105", parkingSpot.get().getApartment());
		assertEquals("A", parkingSpot.get().getBlock());
		assertEquals("105A", parkingSpot.get().getParkingSpotNumber());
		assertEquals("Nathan Lima Batista", parkingSpot.get().getResponsibleName());
	}

}
