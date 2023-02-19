package com.spring.parkingcontrol.dtos;
import javax.validation.constraints.NotBlank;

public class ResidentDto {
	
	@NotBlank
	private String nameResident;
	
	@NotBlank
	private String phoneNumber;
	
	@NotBlank
	private String email;

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

}
