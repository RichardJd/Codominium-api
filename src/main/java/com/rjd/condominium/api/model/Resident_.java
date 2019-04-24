package com.rjd.condominium.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Resident.class)
public abstract class Resident_ extends com.rjd.condominium.api.model.People_ {

	public static volatile ListAttribute<Resident, Vehicle> vehicles;
	public static volatile SingularAttribute<Resident, Long> id;
	public static volatile SingularAttribute<Resident, String> email;
	public static volatile SingularAttribute<Resident, String> apartment;
	public static volatile SingularAttribute<Resident, Account> account;
	public static volatile SingularAttribute<Resident, String> tower;

	public static final String VEHICLES = "vehicles";
	public static final String ID = "id";
	public static final String EMAIL = "email";
	public static final String APARTMENT = "apartment";
	public static final String ACCOUNT = "account";
	public static final String TOWER = "tower";

}

