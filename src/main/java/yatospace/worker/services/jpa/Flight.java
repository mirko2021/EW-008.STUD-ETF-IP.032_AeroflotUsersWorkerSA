package yatospace.worker.services.jpa;

import java.io.Serializable;

/**
 * Подаци који се могу наћи о лету. 
 * @author VM
 * @version 1.0
 */
public class Flight implements Serializable, Cloneable, Comparable<Flight>{
	private static final long serialVersionUID = 7411609370399289059L;
	private String flightId = "";
	private String flightDate = "";
	private String relation = "";
	
	public String getFlightId() {
		return flightId;
	}
	
	public void setFlightId(String flightId) {
		if(flightId == null) flightId = ""; 
		this.flightId = flightId;
	}
	
	public String getFlightDate() {
		return flightDate;
	}
	
	public void setFlightDate(String flightDate) {
		if(flightDate == null)  flightDate = ""; 
		this.flightDate = flightDate;
	}
	
	@Override
	public String toString() {
		return flightId; 
	}
	
	@Override 
	public int hashCode() {
		return flightId.hashCode(); 
	}
	
	@Override
	public Flight clone() {
		Flight flight = new Flight();
		flight.flightDate = flightDate;
		flight.flightId = flightId;
		flight.relation = relation;
		return flight; 
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof Flight) {
			Flight flight = (Flight) object; 
			return flight.flightId.contentEquals(flightId);
		}
		return false; 
	}
	
	@Override
	public int compareTo(Flight o) {
		return flightId.compareTo(o.flightId);
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		if(relation==null) relation = "";
		this.relation = relation;
	}
}
