package yatospace.worker.services.jpa;

import java.io.Serializable;

/**
 * Закон о порукама. 
 */
public class Message implements Serializable, Cloneable, Comparable<Message>{
	private static final long serialVersionUID = 5353254322386032954L;
	private String messageId = "";
	
	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		if(messageId == null) messageId = ""; 
		this.messageId = messageId;
	}

	@Override
	public int compareTo(Message o) {
		return 0;
	}
}
