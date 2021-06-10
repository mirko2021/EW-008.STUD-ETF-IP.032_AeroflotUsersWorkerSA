package yatospace.worker.services.io;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import yatospace.worker.services.db.YatospaceDBConnectionPool;
import yatospace.worker.services.jpa.Flight;

/**
 * Адаптер према бази података који се односи на летове. 
 * @author VM
 * @version 1.0 
 */
public class FligthsDAO {
	private YatospaceDBConnectionPool pool;
	
	public FligthsDAO(YatospaceDBConnectionPool pool) {
		if(pool==null) throw new NullPointerException();
		this.pool = pool; 
	}

	public YatospaceDBConnectionPool getPool() {
		return pool;
	}
	
	public List<Flight> list(){
		try {
			Connection connection = pool.checkOut();
			ArrayList<Flight> flights = new ArrayList<>(); 
			
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/list_flight.sql"))){
				while(scanner.hasNext()) {
					sql+= scanner.nextLine();
				}
			}
			
			
			if(sql.trim().length()==0) return new ArrayList<>(); 
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						Flight flight = new Flight();
						flight.setFlightDate(rs.getString("fly_date")); 
						flight.setFlightId(rs.getString("fly_id"));
						flight.setRelation(rs.getString("relation")); 
						flights.add(flight); 
					}
				}
			}finally {
				pool.checkIn(connection);
			}
		
			return flights;
		}catch(RuntimeException ex) {
			ex.printStackTrace(); 
			return new ArrayList<>(); 
		}catch(Exception ex) {
			ex.printStackTrace(); 
			return new ArrayList<>();
		}
	}
	
	public Flight get(String fligthId) {
		try {
			Connection connection = pool.checkOut();
			
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/get_flight.sql"))){
				while(scanner.hasNext()) {
					sql+= scanner.nextLine();
				}
			}
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, fligthId);
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						Flight flight = new Flight();
						flight.setFlightDate(rs.getString("fly_date")); 
						flight.setFlightId(rs.getString("fly_id"));
						flight.setRelation(rs.getString("relation"));
						return flight; 
					}
				}
			}finally {
				pool.checkIn(connection); 
			}
			
			return null; 
		}catch(Exception ex) {
			return null; 
		}
	}
	
	public void insert(Flight flight) {
		try {
			Connection connection = pool.checkOut();
			
			String sql = "";
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/insert_flight.sql"))){
				while(scanner.hasNext()) {
					sql+= scanner.nextLine();
				}
			}
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, flight.getFlightId());
				statement.setString(2, flight.getFlightDate());
				statement.setString(3, flight.getRelation());
				statement.execute(); 
			}finally {
				pool.checkIn(connection); 
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException();
		}
	}
	
	public void update(String oldId, Flight flight) {
		try {
			Connection connection = pool.checkOut();
			
			String sql = "";
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/update_flight.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine();
				}
			}
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, flight.getFlightId());
				statement.setString(2, flight.getFlightDate());
				statement.setString(3, flight.getRelation()); 
				statement.setString(4, oldId);
				statement.execute();
			}finally {
				pool.checkIn(connection); 
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException();
		}
	}
	
	public void delete(String flightId) {
		try {
			Connection connection = pool.checkOut();
			
			String sql = "";
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/remove_flight.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine();
				}
			}
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, flightId);
				statement.execute();
			}finally {
				pool.checkIn(connection); 
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException();
		}
	}
	
	public void put(Flight flight) {
		try {
			if(get(flight.getFlightId())==null) insert(flight);
			else update(flight.getFlightId(), flight);
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException();
		}
	}
}
