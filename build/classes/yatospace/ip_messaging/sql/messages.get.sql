SELECT message_id,  message_value, vreme, vreme_promene, 
       username, observed
FROM  yi_users_messages_ip_aeroline_company
WHERE  message_id = ?