package yatospace.ip_messaging.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Модел за корисничке поруке. 
 * @author VM
 * @version 1.0
 */
public class UserMessage implements Serializable, Cloneable, Comparable<UserMessage>{
	private static final long serialVersionUID = 8834822637759492140L;
	
	private String messageId = "";
	private String messageContent  = "";
	private Date messageDate = new Date();
	private Date changeDate = new Date();
	private boolean messageRead = false; 
	private String username = "";
	
	public String getMessageId() {
		return messageId;
	}
	
	public void setMessageId(String messageId) {
		if(messageId==null) messageId = ""; 
		this.messageId = messageId;
	}
	
	public String getMessageContent() {
		return messageContent;
	}
	
	
	public void setMessageContent(String messageContent) {
		if(messageContent==null) messageContent=""; 
		this.messageContent = messageContent;
	}
	
	public Date getMessageDate() {
		return messageDate;
	}
	
	public void setMessageDate(Date messageDate) {
		if(messageDate==null) return; 
		this.messageDate = messageDate;
	}
	
	
	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		if(changeDate==null) return; 
		this.changeDate = changeDate;
	}

	public boolean isMessageRead() {
		return messageRead;
	}
	
	public void setMessageRead(boolean messageRead) {
		this.messageRead = messageRead;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		if(username==null) username = ""; 
		this.username = username;
	}

	@Override 
	public String toString() {
		return messageId; 
	}
	
	@Override
	public int hashCode() {
		return messageId.hashCode(); 
	}
	
	@Override
	public UserMessage clone() {
		UserMessage message = new UserMessage();
		message.messageContent = messageContent; 
		message.messageDate = messageDate; 
		message.messageId = messageId; 
		message.messageRead = messageRead;
		message.username =  username;
		message.changeDate = changeDate; 
		return message; 
	}
	
	@Override 
	public boolean equals(Object object) {
		if(object instanceof UserMessage) {
			UserMessage message = (UserMessage) object;
			return messageId.contentEquals(message.messageId); 
		}
		return false; 
	}
	
	@Override
	public int compareTo(UserMessage o) {
		return messageId.compareTo(o.messageId);
	} 
}
