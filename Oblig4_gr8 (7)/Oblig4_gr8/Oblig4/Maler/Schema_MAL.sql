DROP SCHEMA IF EXISTS dat108oblig4 CASCADE;
CREATE SCHEMA dat108oblig4;
SET search_path = dat108oblig4;

CREATE TABLE deltager
(
 
   fornavn         	CHARACTER VARYING (20),
   etternavn      	CHARACTER VARYING (20),
   mobil          	Integer,
   passord        	CHARACTER VARYING (100),
   kjonn          	CHARACTER VARYING (20),
   PRIMARY KEY (mobil)

);

INSERT INTO deltager VALUES 
	('Jens', 'Persen', '12345678', 'qwerasdf', 'mann'),
	('Jonas', 'Erikson', '87654321', 'asdfqwer', 'mann');