USE yatospace_db; 

DROP TABLE IF EXISTS yi_ip_reservations; 
CREATE TABLE yi_ip_reservations(
	id_reservation INTEGER PRIMARY KEY AUTO_INCREMENT,
    reservation_id VARCHAR(100) NOT NULL, 
    fly_id VARCHAR(100) NOT NULL, 
    place_count INTEGER, 
	article_description TEXT, 
    article_transport_file VARCHAR(300), 
    username VARCHAR(100)
);