package com.rjd.condominium.api.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class Address {

	@Size(max = 60)
	private String address;
	
	@Size(max = 10)
	private String number;
	
	@Size(max = 60)
	private String complement;
	
	@Size(max = 20)
	private String zipCode;
	
	@Size(max = 60)
	private String district;
	
	@Size(max = 60)
	private String city;
	
	@Size(max = 2)
	private String state;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
