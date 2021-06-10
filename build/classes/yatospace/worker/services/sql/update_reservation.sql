UPDATE yi_ip_reservations SET 
	reservation_id = ?, 
	fly_id = ?, 
	username = ?, 
	place_count = ?, 
	article_description = ?, 
	article_transport_file = ?
WHERE reservation_id = ?;