package com.rjd.condominium.api.model;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@MappedSuperclass
public class People {

	@NotNull
	@Size(min = 5, max = 60)
	private String name;

	@NotNull
	@CPF
	@Size(max = 20)
	private String cpf;

	@Size(max = 20)
	private String rg;
	
	@Size(max = 20)
	private String phone;
	
	@Size(max = 20)
	private String cell;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}
}
