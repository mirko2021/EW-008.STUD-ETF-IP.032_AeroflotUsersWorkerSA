package yatospace.ip_messaging.io;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Scanner;

import yatospace.worker.services.db.YatospaceDBConnectionPool;

/**
 * Операције са упитима за временом на серверу базе података. 
 * @author VM
 * @version 1.0
 */
public class SQLTimestampDAO {
	private YatospaceDBConnectionPool connectionPool;

	public SQLTimestampDAO(YatospaceDBConnectionPool connectionPool) {
		if(connectionPool==null) throw new NullPointerException();
		this.connectionPool = connectionPool; 
	}
	
	public YatospaceDBConnectionPool getConnectionPool() {
		return connectionPool;
	}
	
	public Date currentDBTimestamp() {
		try {
			String sql = "";
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/ip_messaging/sql/sql.current_timestamp.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n"; 
				}
			}
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				try (ResultSet rs = statement.executeQuery()){
					while(rs.next()){
						return rs.getTimestamp(1);
					}
				}
			}finally {
				connectionPool.checkIn(conn); 
			}
			return null;
		}catch(Exception ex) {
			return null;
		}
	}
	
	public Date currentDBDate() {
		try {
			String sql = "";
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/ip_messaging/sql/sql.current_date.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n"; 
				}
			}
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				try (ResultSet rs = statement.executeQuery()){
					while(rs.next()){
						return rs.getDate(1);
					}
				}
			}finally {
				connectionPool.checkIn(conn); 
			}
			return null;
		}catch(Exception ex) {
			return null;
		}
	}
	
	public Date currentDBTime() {
		try {
			String sql = "";
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/ip_messaging/sql/sql.current_time.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n"; 
				}
			}
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				try (ResultSet rs = statement.executeQuery()){
					while(rs.next()){
						return rs.getTime(1);
					}
				}
			}finally {
				connectionPool.checkIn(conn); 
			}
			return null;
		}catch(Exception ex) {
			return null;
		}
	}
}
