package yatospace.ip_messaging.io;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import yatospace.ip_messaging.model.UserMessage;
import yatospace.ip_messaging.object.IPMessageDTO;
import yatospace.worker.services.db.YatospaceDBConnectionPool;

/**
 * Адаптер према бази података, када су у питању корисничке 
 * поруке. 
 * @author VM
 * @version 1.0
 */
public class IPMessageDAO {
	private YatospaceDBConnectionPool connectionPool;
	
	public IPMessageDAO(YatospaceDBConnectionPool connectionPool) {
		if(connectionPool==null) throw new NullPointerException();
		this.connectionPool = connectionPool; 
	}
	
	public YatospaceDBConnectionPool getConnectionPool() {
		return connectionPool;
	}
	
	public List<IPMessageDTO> listReviewedMessage(String username){
		if(username==null) return new ArrayList<>();
		try {
			ArrayList<IPMessageDTO> messages = new ArrayList<>();
			
			String sql = "";
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/ip_messaging/sql/messages.list_previewed.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n"; 
				}
			}
			
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement=conn.prepareStatement(sql)){
				statement.setString(1, username); 
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						IPMessageDTO dto = new IPMessageDTO();
						UserMessage userMessage = new UserMessage();
						String userName = rs.getString("username");
						String messageId = rs.getString("message_id");
						String messageContent = rs.getString("message_value");
						boolean visited = rs.getBoolean("observed"); 
						Date created = rs.getTimestamp("vreme"); 
						Date modified = rs.getTimestamp("vreme_promene");
						if(messageContent==null) messageContent = "";
						userMessage.setChangeDate(modified); 
						userMessage.setMessageContent(messageContent); 
						userMessage.setMessageDate(created); 
						userMessage.setMessageId(messageId); 
						userMessage.setMessageRead(visited); 
						userMessage.setUsername(userName); 
						dto.setMessage(userMessage);
						messages.add(dto); 
					}
				}
			}finally {
				connectionPool.checkIn(conn); 
			}
		
			
			return messages;
		}catch(RuntimeException ex) {
			return new ArrayList<>();
		}catch(Exception ex) {
			return new ArrayList<>();
		}
	}
	
	public List<IPMessageDTO> listUnviewedMessage(String username){
		if(username==null) return new ArrayList<>(); 
		try {
			ArrayList<IPMessageDTO> messages = new ArrayList<>();
			
			String sql = "";
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/ip_messaging/sql/messages.list_nonviewed.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n"; 
				}
			}
			
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement=conn.prepareStatement(sql)){
				statement.setString(1, username); 
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						IPMessageDTO dto = new IPMessageDTO();
						UserMessage userMessage = new UserMessage();
						String userName = rs.getString("username");
						String messageId = rs.getString("message_id");
						String messageContent = rs.getString("message_value");
						boolean visited = rs.getBoolean("observed"); 
						Date created = rs.getTimestamp("vreme"); 
						Date modified = rs.getTimestamp("vreme_promene");
						if(messageContent==null) messageContent = "";
						userMessage.setChangeDate(modified); 
						userMessage.setMessageContent(messageContent); 
						userMessage.setMessageDate(created); 
						userMessage.setMessageId(messageId); 
						userMessage.setMessageRead(visited); 
						userMessage.setUsername(userName); 
						dto.setMessage(userMessage);
						messages.add(dto); 
					}
				}
			}finally {
				connectionPool.checkIn(conn); 
			}
		
			
			return messages;
		}catch(RuntimeException ex) {
			return new ArrayList<>();
		}catch(Exception ex) {
			return new ArrayList<>();
		}
	}
	
	public IPMessageDTO get(String messageId) {
		if(messageId==null) return null;
		try {
			IPMessageDTO message = null;
			
			String sql = "";
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/ip_messaging/sql/messages.get.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n"; 
				}
			}
			
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement=conn.prepareStatement(sql)){
				statement.setString(1, messageId); 
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						IPMessageDTO dto = new IPMessageDTO();
						UserMessage userMessage = new UserMessage();
						String userName = rs.getString("username");
						String messageID = rs.getString("message_id");
						String messageContent = rs.getString("message_value");
						boolean visited = rs.getBoolean("observed"); 
						Date created = rs.getTimestamp("vreme"); 
						Date modified = rs.getTimestamp("vreme_promene");
						if(messageContent==null) messageContent = "";
						userMessage.setChangeDate(modified); 
						userMessage.setMessageContent(messageContent); 
						userMessage.setMessageDate(created); 
						userMessage.setMessageId(messageID); 
						userMessage.setMessageRead(visited); 
						userMessage.setUsername(userName); 
						dto.setMessage(userMessage);
						message = dto;
					}
				}
			}finally {
				connectionPool.checkIn(conn); 
			}
		
			return message;
		}catch(RuntimeException ex) {
			return null;
		}catch(Exception ex) {
			return null;
		}
	}
	
	public void insert(IPMessageDTO data) {
		if(data==null) return; 
		if(data.getMessage()==null) return;
		try {
			String sql = "";
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/ip_messaging/sql/messages.op_insert.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n"; 
				}
			}
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement=conn.prepareStatement(sql)){
				statement.setString(1, data.getMessage().getMessageId()); 
				statement.setString(2, data.getMessage().getMessageContent()); 
				statement.setString(3, data.getMessage().getUsername()); 
				statement.execute(); 
			}finally {
				connectionPool.checkIn(conn); 
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	} 
	
	public void delete(String messageId) {
		if(messageId==null) return; 
		try {
			String sql = "";
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/ip_messaging/sql/messages.op_delete.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n"; 
				}
			}
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement=conn.prepareStatement(sql)){
				statement.setString(1, messageId); 
				statement.execute(); 
			}finally {
				connectionPool.checkIn(conn); 
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void update(String oldMessageId, IPMessageDTO neoData) {	
		if(oldMessageId==null) return; 
		if(neoData==null) return;
		if(neoData.getMessage()==null) return;
		try {
			String sql = "";
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/ip_messaging/sql/messages.op_update.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n"; 
				}
			}
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement=conn.prepareStatement(sql)){
				statement.setString(1, neoData.getMessage().getMessageId());
				statement.setString(2, neoData.getMessage().getMessageContent());
				statement.setTimestamp(3, new java.sql.Timestamp(neoData.getMessage().getChangeDate().getTime())); 
				statement.setString(4, neoData.getMessage().getUsername());
				statement.setString(5, oldMessageId);
				statement.execute(); 
			}finally {
				connectionPool.checkIn(conn); 
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public List<IPMessageDTO> listReviewedMessage(){
		try {
			ArrayList<IPMessageDTO> messages = new ArrayList<>();
			
			String sql = "";
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/ip_messaging/sql/messages.list_all_previewed.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n"; 
				}
			}
			
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement=conn.prepareStatement(sql)){
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						IPMessageDTO dto = new IPMessageDTO();
						UserMessage userMessage = new UserMessage();
						String userName = rs.getString("username");
						String messageId = rs.getString("message_id");
						String messageContent = rs.getString("message_value");
						boolean visited = rs.getBoolean("observed"); 
						Date created = rs.getTimestamp("vreme"); 
						Date modified = rs.getTimestamp("vreme_promene");
						if(messageContent==null) messageContent = "";
						userMessage.setChangeDate(modified); 
						userMessage.setMessageContent(messageContent); 
						userMessage.setMessageDate(created); 
						userMessage.setMessageId(messageId); 
						userMessage.setMessageRead(visited); 
						userMessage.setUsername(userName); 
						dto.setMessage(userMessage);
						messages.add(dto); 
					}
				}
			}finally {
				connectionPool.checkIn(conn); 
			}
		
			
			return messages;
		}catch(RuntimeException ex) {
			return new ArrayList<>();
		}catch(Exception ex) {
			return new ArrayList<>();
		}
	}
	
	public List<IPMessageDTO> listUnviewedMessage(){
		try {
			ArrayList<IPMessageDTO> messages = new ArrayList<>();
			
			String sql = "";
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/ip_messaging/sql/messages.list_all_nonviewed.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n"; 
				}
			}
			
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement=conn.prepareStatement(sql)){
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						IPMessageDTO dto = new IPMessageDTO();
						UserMessage userMessage = new UserMessage();
						String userName = rs.getString("username");
						String messageId = rs.getString("message_id");
						String messageContent = rs.getString("message_value");
						boolean visited = rs.getBoolean("observed"); 
						Date created = rs.getTimestamp("vreme"); 
						Date modified = rs.getTimestamp("vreme_promene");
						if(messageContent==null) messageContent = "";
						userMessage.setChangeDate(modified); 
						userMessage.setMessageContent(messageContent); 
						userMessage.setMessageDate(created); 
						userMessage.setMessageId(messageId); 
						userMessage.setMessageRead(visited); 
						userMessage.setUsername(userName); 
						dto.setMessage(userMessage);
						messages.add(dto); 
					}
				}
			}finally {
				connectionPool.checkIn(conn); 
			}
		
			
			return messages;
		}catch(RuntimeException ex) {
			return new ArrayList<>();
		}catch(Exception ex) {
			return new ArrayList<>();
		}
	}
	
	public void markRead(String messageId) {
		if(messageId==null) throw new RuntimeException();
		try {
			String sql = "";
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/ip_messaging/sql/messages.mark_read.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n"; 
				}
			}
			
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement=conn.prepareStatement(sql)){
				statement.setString(1, messageId); 
				statement.execute();
			}finally {
				connectionPool.checkIn(conn); 
			}
		}catch(RuntimeException ex) {
			throw ex;
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
