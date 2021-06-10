package yatospace.ip_messaging.util;

import java.text.SimpleDateFormat;

import yatospace.ip_messaging.model.UserMessage;

/**
 * Механизми форматирања података за испис на ХТМЛ, 
 * а у објекту поруке. 
 * @author VM
 * @version 1.0
 */
public class UserMessageFormatEnviroment {
	private UserMessage message = new UserMessage();
	
	public UserMessageFormatEnviroment() {}
	public UserMessageFormatEnviroment(UserMessage message) {
		if(message!=null) this.message = message;
	}
	
	public UserMessage getMessage() {
		return message;
	}
	
	public void setMessage(UserMessage message) {
		if(message==null) message = new UserMessage();
		this.message = message;
	}
	
	public String escapeUsername() {
		return message.getUsername().replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;").replaceAll("'","&apos;");
	}
	
	public String escapeContent() {
		return message.getMessageContent().replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;").replaceAll("'","&apos;"); 
	}
	
	public String escapeMessageId() {
		return message.getMessageId().replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;").replaceAll("'","&apos;"); 
	}
	
	
	
	public String createdTimestampStandard() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss");
		return sdf.format(message.getMessageDate()); 
	}
	
	public String createdDateStandard() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
		return sdf.format(message.getMessageDate()); 
	}
	
	public String createdTimeStandard() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(message.getMessageDate()); 
	}
	
	
	public String modifiedTimestampStandard() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss");
		return sdf.format(message.getChangeDate()); 
	}
	
	public String modifiedDateStandard() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
		return sdf.format(message.getChangeDate()); 
	}
	
	
	public String modifiedTimeStandard() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(message.getChangeDate()); 
	}
	
	
	public String createdTimestampSQL() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(message.getMessageDate()); 
	}
	
	public String createdDateSQL() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(message.getMessageDate()); 
	}
	
	public String createdTimeSQL() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(message.getMessageDate()); 
	}
	
	
	public String modifiedTimestampSQL() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(message.getChangeDate()); 
	}
	
	public String modifiedDateSQL() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(message.getChangeDate()); 
	}
	
	public String modifiedTimeSQL() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(message.getChangeDate()); 
	}
	
	public String createdTimestampHTML() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(message.getMessageDate()); 
	}
	
	public String createdDateHTML() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(message.getMessageDate()); 
	}
	
	public String createdTimeHTML() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(message.getMessageDate()); 
	}
	
	
	public String modifiedTimestampHTML() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(message.getChangeDate()); 
	}
	
	public String modifiedDateHTML() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(message.getChangeDate()); 
	}
	
	public String modifiedTimeHTML() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(message.getChangeDate()); 
	}
}
