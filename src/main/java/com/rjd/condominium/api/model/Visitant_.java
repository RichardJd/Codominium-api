package com.rjd.condominium.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Visitant.class)
public abstract class Visitant_ {

	public static volatile SingularAttribute<Visitant, Address> address;
	public static volatile SingularAttribute<Visitant, String> rg;
	public static volatile SingularAttribute<Visitant, String> phone;
	public static volatile SingularAttribute<Visitant, String> name;
	public static volatile SingularAttribute<Visitant, String> cpf;
	public static volatile ListAttribute<Visitant, Vehicle> vehicles;
	public static volatile SingularAttribute<Visitant, Long> id;
	public static volatile SingularAttribute<Visitant, String> cell;

	public static final String ADDRESS = "address";
	public static final String RG = "rg";
	public static final String PHONE = "phone";
	public static final String NAME = "name";
	public static final String CPF = "cpf";
	public static final String VEHICLES = "vehicles";
	public static final String ID = "id";
	public static final String CELL = "cell";

}

