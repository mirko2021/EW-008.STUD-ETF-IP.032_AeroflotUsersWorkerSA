package yatospace.worker.web.registration;

/**
 * Објекти са основним подацима о корисниу. 
 * @author VM
 * @version 1.0
 */
public class UserBasicDTO {
	private String username = ""; 
	private String userId = ""; 
	private String passwordcode = "";
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		if(username == null) username = "";
		this.username = username;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		if(userId == null) userId = ""; 
		this.userId = userId;
	}
	public String getPasswordcode() {
		return passwordcode;
	}
	public void setPasswordcode(String passwordcode) {
		if(passwordcode==null) passwordcode = ""; 
		this.passwordcode = passwordcode;
	} 
}
