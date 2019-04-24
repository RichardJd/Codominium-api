package com.rjd.condominium.api.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Visit.class)
public abstract class Visit_ {

	public static volatile SingularAttribute<Visit, LocalDateTime> finalDate;
	public static volatile SingularAttribute<Visit, Visitant> visitant;
	public static volatile SingularAttribute<Visit, Long> id;
	public static volatile SingularAttribute<Visit, LocalDateTime> startDate;
	public static volatile SingularAttribute<Visit, Boolean> open;
	public static volatile SingularAttribute<Visit, Resident> resident;

	public static final String FINAL_DATE = "finalDate";
	public static final String VISITANT = "visitant";
	public static final String ID = "id";
	public static final String START_DATE = "startDate";
	public static final String OPEN = "open";
	public static final String RESIDENT = "resident";

}

