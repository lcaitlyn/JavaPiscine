DROP TABLE IF EXISTS simple_user;

CREATE TABLE simple_user (
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(10),
	last_name VARCHAR(10),
	age INT
);

DROP TABLE IF EXISTS car;

CREATE TABLE car (
	id SERIAL PRIMARY KEY,
	name VARCHAR(10),
	speed INT
);

DROP TABLE IF EXISTS TestClass;

CREATE TABLE TestClass (
	id SERIAL PRIMARY KEY,
	string_with_length_test VARCHAR(50),
	string_without_length_test VARCHAR(256),
	int_test INT,
	boolean_test BOOLEAN,
	long_test BIGINT,
	float_test REAL,
	double_test DOUBLE PRECISION
);

