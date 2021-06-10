package yatospace.worker.web.registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import yatospace.worker.services.db.YatospaceDBConnectionPool;

/**
 * Основна контрола кад су у питању корисници и основни подаци
 * и примопредаја података из базе података. 
 * @author VM
 * @version 1.0
 */
public class UserBasicDAO {
	private YatospaceDBConnectionPool pool;

	public YatospaceDBConnectionPool getPool() {
		return pool;
	}
	
	public UserBasicDAO(YatospaceDBConnectionPool pool) {
		if(pool==null) throw new NullPointerException();
		this.pool = pool;
	}
	
	public UserBasicDTO get(String username) {
		try {
			String sql = "";
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/get_user_basics.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			Connection connection = pool.checkOut(); 
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, username);
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						UserBasicDTO dto = new UserBasicDTO();
						dto.setUserId(rs.getString("id_user"));
						dto.setPasswordcode(rs.getString("passwordcode"));
						dto.setUsername(rs.getString("username"));
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
