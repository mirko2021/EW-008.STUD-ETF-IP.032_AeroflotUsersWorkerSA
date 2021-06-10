SELECT communication_id, username, contact_email
FROM yi_users_communications_ip_aerolines_company
WHERE contact_email = ? ORDER BY username ASC, contact_email ASC;  