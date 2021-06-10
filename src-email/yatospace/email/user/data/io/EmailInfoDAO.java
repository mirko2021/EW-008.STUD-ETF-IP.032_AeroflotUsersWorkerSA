package yatospace.email.user.data.io;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import yatospace.email.data.model.UserEmail;
import yatospace.email.data.object.EmailDTO;
import yatospace.worker.services.db.YatospaceDBConnectionPool;

/**
 * Адаптер који је потребан за баратање и 
 * валидацију корисника, ондсно прихватања 
 * електронске поште на његово име. 
 * @author VM
 * @version 1.0
 */
public class EmailInfoDAO {
	private YatospaceDBConnectionPool connectionPool; 
	
	public EmailInfoDAO(YatospaceDBConnectionPool connectionPool) {
		if(connectionPool==null) throw new RuntimeException();
		this.connectionPool = connectionPool; 
	}

	public YatospaceDBConnectionPool getConnectionPool() {
		return connectionPool;
	}
	
	public List<String> listUsers(){
		try {
			ArrayList<String> result = new ArrayList<>();
			
			String sql = "";
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/email/user/data/sql/users.list.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n"; 
				}
			}
			
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement=conn.prepareStatement(sql)){
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						String username = rs.getString("username");
						result.add(username); 
					}
				}
			}finally {
				connectionPool.checkIn(conn); 
			}
			
			return result; 
		}catch(RuntimeException ex) {
			return new ArrayList<>();
		}catch(Exception ex) {
			return new ArrayList<>();
		}
	}
	
	public String getUser(String userName){
		if(userName==null) return null;
		try {
			String user = null;
			
			String sql = "";
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/email/user/data/sql/users.get.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n"; 
				}
			}
			
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement=conn.prepareStatement(sql)){
				statement.setString(1, userName); 
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						String username = rs.getString("username");
						user = username; 
					}
				}
			}finally {
				connectionPool.checkIn(conn); 
			}
			
			return user;
		}catch(RuntimeException ex) {
			return null;
		}catch(Exception ex) {
			return null;
		}
	}
	
	public List<EmailDTO> listEmails() {
		try {
			ArrayList<EmailDTO> emails = new ArrayList<>();
			
			String sql = "";
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/email/user/data/sql/emails.list.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n"; 
				}
			}
			
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement=conn.prepareStatement(sql)){
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						UserEmail userEmail = new UserEmail();
						EmailDTO emailDTO = new EmailDTO();
						String username = rs.getString("username");
						String contactEmail = rs.getString("contact_email");
						String emailId = rs.getString("communication_id"); 
						userEmail.setEmailAddress(contactEmail);
						userEmail.setEmailId(emailId);
						userEmail.setUsername(username);
						emailDTO.setUserEmail(userEmail);
						emails.add(emailDTO); 
					}
				}
			}finally {
				connectionPool.checkIn(conn); 
			}
			
			return emails; 
		}catch(RuntimeException ex) {
			return new ArrayList<>();
		}catch(Exception ex) {
			return new ArrayList<>();
		}
	}
	
	public List<EmailDTO> listEmailsByAddress(String emailAddress) {
		if(emailAddress==null) return new ArrayList<>();
		try {
			ArrayList<EmailDTO> emails = new ArrayList<>();
			
			String sql = "";
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/email/user/data/sql/emails.list_by_address.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n"; 
				}
			}
			
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement=conn.prepareStatement(sql)){
				statement.setString(1, emailAddress); 
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						UserEmail userEmail = new UserEmail();
						EmailDTO emailDTO = new EmailDTO();
						String username = rs.getString("username");
						String contactEmail = rs.getString("contact_email");
						String emailId = rs.getString("communication_id"); 
						userEmail.setEmailAddress(contactEmail);
						userEmail.setEmailId(emailId);
						userEmail.setUsername(username);
						emailDTO.setUserEmail(userEmail);
						emails.add(emailDTO); 
					}
				}
			}finally {
				connectionPool.checkIn(conn); 
			}
			
			return emails; 
		}catch(RuntimeException ex) {
			return new ArrayList<>();
		}catch(Exception ex) {
			return new ArrayList<>();
		}
	}
	
	public EmailDTO getEmailByUsername(String userName) {
		if(userName==null) return null;
		try {
			EmailDTO email = null;
			
			String sql = "";
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/email/user/data/sql/emails.get_by_user.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n"; 
				}
			}
			
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement=conn.prepareStatement(sql)){
				statement.setString(1, userName); 
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						UserEmail userEmail = new UserEmail();
						EmailDTO emailDTO = new EmailDTO();
						String username = rs.getString("username");
						String contactEmail = rs.getString("contact_email");
						String emailId = rs.getString("communication_id"); 
						userEmail.setEmailAddress(contactEmail);
						userEmail.setEmailId(emailId);
						userEmail.setUsername(username);
						emailDTO.setUserEmail(userEmail);
						email = emailDTO; 
					}
				}
			}finally {
				connectionPool.checkIn(conn); 
			}
			
			return email; 
		}catch(RuntimeException ex) {
			return null;
		}catch(Exception ex) {
			return null;
		}
	}
	
	public EmailDTO getEmailById(String emailIdentificator) {
		if(emailIdentificator == null) return null; 
		try {
			EmailDTO email = null;
			
			String sql = "";
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/email/user/data/sql/emails.get_by_id.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n"; 
				}
			}
			
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement=conn.prepareStatement(sql)){
				statement.setString(1, emailIdentificator); 
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						UserEmail userEmail = new UserEmail();
						EmailDTO emailDTO = new EmailDTO();
						String username = rs.getString("username");
						String contactEmail = rs.getString("contact_email");
						String emailId = rs.getString("communication_id"); 
						userEmail.setEmailAddress(contactEmail);
						userEmail.setEmailId(emailId);
						userEmail.setUsername(username);
						emailDTO.setUserEmail(userEmail);
						email = emailDTO; 
					}
				}
			}finally {
				connectionPool.checkIn(conn); 
			}
			
			return email; 
		}catch(RuntimeException ex) {
			return null;
		}catch(Exception ex) {
			return null;
		}
	}
	
	public void insert(EmailDTO data) {
		if(data==null) return; 
		if(data.getUserEmail()==null) return;
		try {
			String sql = "";
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/email/user/data/sql/emails.op_insert.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n"; 
				}
			}
			Connection conn = connectionPool.checkOut();
			try(PreparedStatement statement=conn.prepareStatement(sql)){
				statement.setString(1, data.getUserEmail().getEmailId()); 
				statement.setString(2, data.getUserEmail().getUsername());
				statement.setString(3, data.getUserEmail().getEmailAddress());
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
	
	public void update(String oldEmailId, EmailDTO data) {
		if(oldEmailId==null) return; 
		if(data==null) return; 
		if(data.getUserEmail()==null) return;
		try {
			String sql = "";
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/email/user/data/sql/emails.op_update.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n"; 
				}
			}
			Connection conn = connectionPool.checkOut();
			try(PreparedStatement statement=conn.prepareStatement(sql)){
				statement.setString(1, data.getUserEmail().getEmailId()); 
				statement.setString(2, data.getUserEmail().getUsername());
				statement.setString(3, data.getUserEmail().getEmailAddress());
				statement.setString(4, oldEmailId);
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
	
	public void remove(String oldEmailId) {
		if(oldEmailId==null) return; 
		try {
			String sql = "";
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/email/user/data/sql/emails.op_delete.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n"; 
				}
			}
			Connection conn = connectionPool.checkOut();
			try(PreparedStatement statement=conn.prepareStatement(sql)){
				statement.setString(1, oldEmailId);
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
