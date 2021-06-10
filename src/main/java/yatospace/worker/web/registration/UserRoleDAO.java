package yatospace.worker.web.registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import yatospace.worker.services.db.YatospaceDBConnectionPool;

/**
 * Преузимање података о запослености и административниости, 
 * кад су у питању, подаци о корисницима. 
 * @author VM
 * @version 1.0
 */
public class UserRoleDAO {
	private YatospaceDBConnectionPool pool;

	public YatospaceDBConnectionPool getPool() {
		return pool;
	}
	
	public UserRoleDAO(YatospaceDBConnectionPool pool) {
		if(pool==null) throw new NullPointerException();
		this.pool = pool;
	}
	
	public UserWorkerDTO get(String userId) {
		try {
			Connection connection = pool.checkOut(); 
			
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/get_user_worker_info.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine(); 
				}
			}
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, userId); 
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						UserWorkerDTO dto = new UserWorkerDTO(); 
						dto.setKey(rs.getString("key"));
						dto.setUserId(rs.getString("user")); 
						dto.setValue(rs.getString("value"));
						return dto; 
					}
				}
			}finally {
				pool.checkIn(connection);
			}
			return null;
		}catch(RuntimeException ex) {
			return null;
		}catch(Exception ex) {
			return null;
		}
	}
	
	public UserStandardDTO user(String userId) {
		try {
			Connection connection = pool.checkOut(); 
			
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/get_user_standard_info.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine(); 
				}
			}
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, userId); 
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						UserStandardDTO dto = new UserStandardDTO(); 
						dto.setKey(rs.getString("key"));
						dto.setUserId(rs.getString("user")); 
						dto.setValue(rs.getString("value"));
						return dto; 
					}
				}
			}finally {
				pool.checkIn(connection);
			}
			return null;
		}catch(RuntimeException ex) {
			return null;
		}catch(Exception ex) {
			return null;
		}
	}
}
