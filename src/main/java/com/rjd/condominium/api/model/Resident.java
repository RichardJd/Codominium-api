package com.rjd.condominium.api.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "resident")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Resident extends People {

	private Long id;
	private String email;
	private String tower;
	private String apartment;
}
