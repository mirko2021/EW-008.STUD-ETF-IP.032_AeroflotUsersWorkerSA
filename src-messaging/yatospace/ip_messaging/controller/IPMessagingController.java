package yatospace.ip_messaging.controller;

import java.util.Date;

import yatospace.ip_messaging.io.IPMessageDAO;
import yatospace.ip_messaging.io.IPUserDAO;
import yatospace.ip_messaging.io.SQLTimestampDAO;
import yatospace.ip_messaging.model.UserMessage;
import yatospace.ip_messaging.object.IPMessageDTO;

/**
 * Контролер који се односи на постављање корисничких 
 * порука на сервер.
 * @author VM
 * @version 1.0
 */
public class IPMessagingController {
	private IPMessageDAO messageDAO; 
	private IPUserDAO ipUserDAO; 
	private SQLTimestampDAO timestampDAO;
	
	public IPMessagingController(IPMessageDAO messageDAO, IPUserDAO ipUserDAO, SQLTimestampDAO timestampDAO) {
		if(messageDAO==null) throw new NullPointerException();
		if(ipUserDAO==null) throw new NullPointerException();
		if(timestampDAO==null) throw new NullPointerException();
		this.messageDAO = messageDAO; 
		this.ipUserDAO = ipUserDAO;
		this.timestampDAO = timestampDAO;
	}
	
	public IPMessageDAO getMessageDAO() {
		return messageDAO;
	}
	public IPUserDAO getIpUserDAO() {
		return ipUserDAO;
	}
	public SQLTimestampDAO getTimestampDAO() {
		return timestampDAO;
	} 
	
	public void add(UserMessage message) {
		if(message==null) throw new NullPointerException();
		if(ipUserDAO.getUser(message.getUsername())==null) throw new RuntimeException("User not found.");
		IPMessageDTO dto = new IPMessageDTO();
		dto.setMessage(message);
		messageDAO.insert(dto);
	}
	
	public void delete(String messageId) {
		if(messageId==null) throw new NullPointerException();
		if(messageDAO.get(messageId)==null) throw new RuntimeException("Message not found.");
		messageDAO.delete(messageId);
	}
	
	public void update(String messageId, String username, String neoContent) {
		if(messageId==null) throw new NullPointerException();
		if(username==null) throw new NullPointerException();
		if(neoContent==null) throw new NullPointerException();
		IPMessageDTO dto = messageDAO.get(messageId); 
		if(dto==null) throw new RuntimeException("Message not found.");
		if(dto.getMessage()==null) throw new RuntimeException("Message not found.");
		if(!dto.getMessage().getUsername().contentEquals(username)) throw new RuntimeException("Message username mismatch.");
		Date currentDBServerTimestamp = timestampDAO.currentDBTimestamp(); 
		IPMessageDTO neoDTO = new IPMessageDTO();
		UserMessage userMessage = new UserMessage();
		userMessage.setMessageId(messageId);
		userMessage.setChangeDate(currentDBServerTimestamp);
		userMessage.setMessageContent(neoContent);
		userMessage.setUsername(username);
		neoDTO.setMessage(userMessage);
		messageDAO.update(messageId, neoDTO);
	}
}
