CREATE TABLE account (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	login VARCHAR(20) NOT NULL UNIQUE,
	password VARCHAR(150) NOT NULL
)ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permission (
	id BIGINT(20) PRIMARY KEY,
	descricao VARCHAR(60) NOT NULL
)ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE account_permission (
	id_account BIGINT(20) NOT NULL,
	id_permission BIGINT(20) NOT NULL,
	PRIMARY KEY (id_account, id_permission),
	FOREIGN KEY (id_account) REFERENCES account (id),
	FOREIGN KEY (id_permission) REFERENCES permission (id)
)ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE resident (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(60) NOT NULL,
	cpf VARCHAR(20) NOT NULL,
	rg VARCHAR(20),
	phone VARCHAR(20),
	cell VARCHAR(20),
	email VARCHAR(60),
	tower VARCHAR(10) NOT NULL,
	apartment VARCHAR(10) NOT NULL,
	id_account BIGINT(20) NOT NULL,
	FOREIGN KEY (id_account) REFERENCES account (id)
)ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE visitant (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(60) NOT NULL,
	cpf VARCHAR(20) NOT NULL,
	rg VARCHAR(20),
	phone VARCHAR(20),
	cell VARCHAR(20),
	address VARCHAR(60),
	number VARCHAR(10),
	complement VARCHAR(60),
	zip_code VARCHAR(20),
	district VARCHAR(60),
	city VARCHAR(60),
	state VARCHAR(2)
)ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE vehicle (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	vehicle VARCHAR(30) NOT NULL,
	model VARCHAR(30) NOT NULL,
	color VARCHAR(30) NOT NULL,
	plate VARCHAR(15) NOT NULL
)ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE vehicle_resident (
	id_vehicle BIGINT(20) NOT NULL,
	id_resident BIGINT(20) NOT NULL,
	PRIMARY KEY (id_vehicle, id_resident),
	FOREIGN KEY (id_vehicle) REFERENCES vehicle (id),
	FOREIGN KEY (id_resident) REFERENCES resident (id)
)ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE vehicle_visitant (
	id_vehicle BIGINT(20) NOT NULL,
	id_visitant BIGINT(20) NOT NULL,
	PRIMARY KEY (id_vehicle, id_visitant),
	FOREIGN KEY (id_vehicle) REFERENCES vehicle (id),
	FOREIGN KEY (id_visitant) REFERENCES visitant (id)
)ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE visit (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	start_date DATETIME NOT NULL,
	final_date DATETIME,
	id_resident BIGINT(20) NOT NULL,
	id_visitant BIGINT(20) NOT NULL,
	FOREIGN KEY (id_resident) REFERENCES resident (id),
	FOREIGN KEY (id_visitant) REFERENCES visitant (id)
)ENGINE InnoDB DEFAULT CHARSET=utf8;