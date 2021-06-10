USE yatospace_db; 

DROP TABLE IF EXISTS yi_ip_flights;
CREATE TABLE yi_ip_flights(
	id_fly INTEGER PRIMARY KEY AUTO_INCREMENT,
	fly_id VARCHAR(45) UNIQUE, 
	fly_date TEXT
);

ALTER TABLE yi_ip_flights
ADD relation TEXT;