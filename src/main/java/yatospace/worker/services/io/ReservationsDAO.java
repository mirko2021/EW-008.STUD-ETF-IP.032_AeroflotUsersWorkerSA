package yatospace.worker.services.io;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import yatospace.worker.services.db.YatospaceDBConnectionPool;
import yatospace.worker.services.jpa.Reservation;

/**
 * Адаптери према бази података за резервације.
 * @author VM
 * @version 1.0
 */
public class ReservationsDAO {
	private YatospaceDBConnectionPool pool;
	
	public ReservationsDAO(YatospaceDBConnectionPool pool) {
		if(pool==null) throw new NullPointerException();
		this.pool = pool; 
	}

	public YatospaceDBConnectionPool getPool() {
		return pool;
	}
	
	public List<Reservation> listReservations(){
		ArrayList<Reservation> reservations = new ArrayList<>();
		try {
			String sql = ""; 
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/list_reservation.sql"))) {
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine() + "\n"; 
				}
			}
			
			Connection connection = pool.checkOut(); 
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						Reservation reservation = new Reservation();
						reservation.setUsername(rs.getString("username")); 
						reservation.setReservationId(rs.getString("reservation_id")); 
						reservation.setArticleDescription(rs.getString("article_description"));
						reservation.setFlyId(rs.getString("fly_id")); 
						reservation.setArticleTransportFile(rs.getString("article_transport_file"));
						reservation.setPlaceCount(rs.getInt("place_count"));
						reservations.add(reservation);
					}
				}
			}finally {
				pool.checkIn(connection); 
			}
		}catch(RuntimeException ex) {
			return new ArrayList<>(); 
		}catch(Exception ex) {
			return new ArrayList<>();
		}
		return reservations;
	}
	
	public Reservation get(String reservationId) {
		Reservation reservation = null; 
		try {
			String sql = ""; 
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/get_reservation.sql"))) {
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine() + "\n"; 
				}
			}
			
			Connection connection = pool.checkOut(); 
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, reservationId);
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						reservation = new Reservation();
						reservation.setUsername(rs.getString("username")); 
						reservation.setReservationId(rs.getString("reservation_id")); 
						reservation.setArticleDescription(rs.getString("article_description"));
						reservation.setFlyId(rs.getString("fly_id")); 
						reservation.setArticleTransportFile(rs.getString("article_transport_file"));
						reservation.setPlaceCount(rs.getInt("place_count"));
					}
				}
			}finally {
				pool.checkIn(connection); 
			}
		}catch(RuntimeException ex) {
			return null;
		}catch(Exception ex) {
			return null;
		}
		return reservation; 
	}
	
	public void insert(Reservation reservation) {
		if(reservation==null) return; 
		try {
			String sql = ""; 
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/insert_reservation.sql"))) {
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine() + "\n"; 
				}
			}
			Connection connection = pool.checkOut(); 
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, reservation.getReservationId());
				statement.setString(2, reservation.getFlyId());
				statement.setString(3, reservation.getUsername());
				statement.setInt(4, reservation.getPlaceCount());
				statement.setString(5, reservation.getArticleDescription());
				statement.setString(6, reservation.getArticleTransportFile()); 
				statement.execute();
			}finally{
				pool.checkIn(connection); 
			}
		}catch(RuntimeException ex) {
			throw ex;
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void delete(String reservation) {
		if(reservation==null) return; 
		try {
			String sql = ""; 
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/remove_reservation.sql"))) {
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine() + "\n"; 
				}
			}
			Connection connection = pool.checkOut(); 
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, reservation);
				statement.execute();
			}finally{
				pool.checkIn(connection); 
			}
		}catch(RuntimeException ex) {
			throw ex;
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void update(String oldRID, Reservation reservation) {
		if(oldRID==null) return; 
		if(reservation==null) return; 
		try {
			String sql = ""; 
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/update_reservation.sql"))) {
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine() + "\n"; 
				}
			}
			Connection connection = pool.checkOut(); 
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, reservation.getReservationId());
				statement.setString(2, reservation.getFlyId());
				statement.setString(3, reservation.getUsername());
				statement.setInt(4, reservation.getPlaceCount());
				statement.setString(5, reservation.getArticleDescription());
				statement.setString(6, reservation.getArticleTransportFile()); 
				statement.setString(7, oldRID);
				statement.execute();
			}finally{
				pool.checkIn(connection); 
			}
		}catch(RuntimeException ex) { 
			throw ex;
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void put(Reservation reservation) {
		if(reservation==null) return; 
		if(get(reservation.getReservationId())==null) insert(reservation);
		else update(reservation.getReservationId(), reservation); 
	}
	
	public void setReservationInfo(String reservationId, String astFileName) {
		try {
			String sql = ""; 
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/set_reservation_ast.sql"))) {
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine() + "\n"; 
				}
			}
			Connection connection = pool.checkOut(); 
			try (PreparedStatement statement=connection.prepareStatement(sql)){
				statement.setString(1, astFileName);
				statement.setString(2, reservationId);
				statement.execute();
			}finally {
				pool.checkIn(connection); 
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void resetReservationInfo(String reservationId) {
		try {
			String sql = ""; 
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/reset_reservation_ast.sql"))) {
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine() + "\n"; 
				}
			}
			Connection connection = pool.checkOut(); 
			try (PreparedStatement statement=connection.prepareStatement(sql)){
				statement.setString(1, reservationId); 
				statement.execute(); 
			}finally {
				pool.checkIn(connection); 
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	} 
}
