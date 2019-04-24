package com.rjd.condominium.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Account.class)
public abstract class Account_ {

	public static volatile SingularAttribute<Account, String> password;
	public static volatile SingularAttribute<Account, Long> id;
	public static volatile SingularAttribute<Account, String> login;

	public static final String PASSWORD = "password";
	public static final String ID = "id";
	public static final String LOGIN = "login";

}

