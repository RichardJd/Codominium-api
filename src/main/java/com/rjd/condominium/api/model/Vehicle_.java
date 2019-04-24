package com.rjd.condominium.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Vehicle.class)
public abstract class Vehicle_ {

	public static volatile SingularAttribute<Vehicle, String> color;
	public static volatile ListAttribute<Vehicle, Resident> residents;
	public static volatile SingularAttribute<Vehicle, String> model;
	public static volatile SingularAttribute<Vehicle, String> plate;
	public static volatile SingularAttribute<Vehicle, Long> id;
	public static volatile SingularAttribute<Vehicle, String> vehicle;

	public static final String COLOR = "color";
	public static final String RESIDENTS = "residents";
	public static final String MODEL = "model";
	public static final String PLATE = "plate";
	public static final String ID = "id";
	public static final String VEHICLE = "vehicle";

}

