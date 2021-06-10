package yatospace.worker.web.registration;

import java.io.Serializable;

/**
 * Општи објекат са подацима о томе да 
 * ли је корисник, запослен. 
 * @author VM
 * @version 1.0
 */
public class UserStandardDTO implements Serializable{
	private static final long serialVersionUID = -3776932724879181225L;
	private String key    = "";
	private String value  = ""; 
	private String userId = "";
	
	public String getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}
	public String getUserId() {
		return userId;
	}
	
	public void setKey(String key) {
		if(key==null) key = ""; 
		this.key = key;
	}
	public void setValue(String value) {
		if(value==null) value = ""; 
		this.value = value;
	}
	public void setUserId(String userId) {
		if(userId==null) userId=""; 
		this.userId = userId;
	}
	public boolean isUser() {
		return key.contentEquals("user") && value.contentEquals("true");
	}
}
