package yatospace.ip_messaging.object;

import java.io.Serializable;

import yatospace.ip_messaging.model.UserMessage;

/**
 * Објекат који се везује за баратање са корисничким порукама
 * серверу. 
 * @author VM
 * @version 1.0
 */
public class IPMessageDTO implements Serializable{
	private static final long serialVersionUID = -2697744261268600365L;
	
	private UserMessage message;

	public UserMessage getMessage() {
		return message;
	}

	public void setMessage(UserMessage message) {
		this.message = message;
	} 
}
