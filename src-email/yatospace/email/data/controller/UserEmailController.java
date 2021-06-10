package yatospace.email.data.controller;

import yatospace.email.data.object.EmailDTO;
import yatospace.email.user.data.io.EmailInfoDAO;

/**
 * Контролер и стање при баратању са корисничком 
 * емаил адресом. 
 * @author VM
 * @version 1.0
 */
public class UserEmailController {
	private EmailInfoDAO emailDataSource;
	
	public UserEmailController(EmailInfoDAO emailDataSource) {
		if(emailDataSource==null) throw new RuntimeException();
		this.emailDataSource = emailDataSource;
	}
	
	public EmailInfoDAO getEmailDataSource() {
		return emailDataSource;
	}
	
	public void put(EmailDTO dto) {
		if(dto==null) return;
		if(dto.getUserEmail()==null) return;
		EmailDTO usernameDto = emailDataSource.getEmailByUsername(dto.getUserEmail().getUsername());
		EmailDTO idDto = emailDataSource.getEmailById(dto.getUserEmail().getEmailId());
		if(emailDataSource.getUser(emailDataSource.getUser(dto.getUserEmail().getUsername()))==null) 
			throw new RuntimeException("User not found."); 
		if(usernameDto==null) 
			if(idDto==null) emailDataSource.insert(dto);
			else emailDataSource.update(dto.getUserEmail().getEmailId(), dto);
		else
			if(idDto==null) throw new RuntimeException("Duplicate email for user.");
			else throw new RuntimeException("Duplicate email for user.");	
	}
}
