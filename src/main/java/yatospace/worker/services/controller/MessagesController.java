package yatospace.worker.services.controller;

import yatospace.worker.services.io.MessagesDAO;

/**
 * Контролер који се односи на поруке. 
 * @author VM
 * @version 1.0
 */
public class MessagesController {
	private MessagesDAO messagesDAO;
	
	public MessagesController(MessagesDAO messagesDAO) {
		if(messagesDAO == null) throw new NullPointerException(); 
		this.messagesDAO = messagesDAO; 
	}
	
	public MessagesDAO getMessagesDAO() {
		return messagesDAO;
	}
}
