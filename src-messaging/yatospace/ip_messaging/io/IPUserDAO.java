package yatospace.ip_messaging.io;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import yatospace.worker.services.db.YatospaceDBConnectionPool;

/**
 * Адаптер кад су у питању корисници, а 
 * за потребе баратања са порукама. 
 * @author VM
 * @version 1.0
 */
public class IPUserDAO {
	private YatospaceDBConnectionPool connectionPool;
	
	public IPUserDAO(YatospaceDBConnectionPool connectionPool) {
		if(connectionPool==null) throw new NullPointerException();
		this.connectionPool = connectionPool; 
	}
	
	public YatospaceDBConnectionPool getConnectionPool() {
		return connectionPool;
	}
	
	public List<String> listUsers(){
		try {
			ArrayList<String> result = new ArrayList<>();
			
			String sql = "";
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/ip_messaging/sql/users.list.sql"))){
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
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/ip_messaging/sql/users.get.sql"))){
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
}
