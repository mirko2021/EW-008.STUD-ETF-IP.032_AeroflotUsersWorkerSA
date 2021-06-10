package yatospace.email.data.model;

import java.io.Serializable;

/**
 * Корисничка емаил адреса. 
 * @author VM
 * @version 1.0
 */
public class UserEmail implements Serializable, Cloneable, Comparable<UserEmail>{
	private static final long serialVersionUID = 6849099665510185972L;
	private String emailId = "";
	private String emailAddress = ""; 
	private String username = "";
	
	public void setEmailId(String emailId) {
		if(emailId==null) emailId = ""; 
		this.emailId = emailId;
	}
	public void setEmailAddress(String emailAddress) {
		if(emailAddress==null) emailAddress=null; 
		this.emailAddress = emailAddress;
	}
	public void setUsername(String username) {
		if(username==null) username = "";
		this.username = username;
	}
	
	public String getEmailId() {
		return emailId;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public String getUsername() {
		return username;
	}
	
	@Override
	public String toString() {
		return emailId;
	}
	@Override
	public int hashCode() {
		return emailId.hashCode(); 
	}
	@Override
	public UserEmail clone() {
		UserEmail email = new UserEmail();
		email.emailAddress = emailAddress;
		email.emailId = emailId;
		email.username= username;
		return email; 
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof UserEmail) {
			UserEmail userEmail = (UserEmail) object;
			if(emailId.contentEquals(userEmail.emailId)) return true;  
		}
		return false;
	}
	
	@Override
	public int compareTo(UserEmail o) {
		return emailId.compareTo(o.emailId);
	}
}
