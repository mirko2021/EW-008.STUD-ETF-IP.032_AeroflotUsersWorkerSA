package yatospace.worker.services.jpa;

import java.io.Serializable;

/**
 * Објекат који представља резервације.
 * @author VM
 * @version 1.0
 */
public class Reservation implements Serializable, Cloneable, Comparable<Reservation>{
	private static final long serialVersionUID = -5677198295472116132L;
	
	public Reservation() {}	
	
	private String reservationId = "";
	private String flyId = ""; 
	private String username = ""; 
	private int placeCount = -1; 
	private String articleDescription = ""; 
	private String articleTransportFile = "";

	public String getReservationId() {
		return reservationId;
	}
	public void setReservationId(String reservationId) {
		if(reservationId==null) reservationId = ""; 
		this.reservationId = reservationId;
	}
	public String getFlyId() {
		return flyId;
	}
	public void setFlyId(String flyId) {
		if(flyId==null) flyId = ""; 
		this.flyId = flyId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		if(username==null) username = ""; 
		this.username = username;
	}
	public int getPlaceCount() {
		return placeCount;
	}
	public void setPlaceCount(int placeCount) {
		if(placeCount<0) placeCount=-1; 
		this.placeCount = placeCount;
	}
	public String getArticleDescription() {
		return articleDescription;
	}
	public void setArticleDescription(String articleDescription) {
		if(articleDescription==null) articleDescription = ""; 
		this.articleDescription = articleDescription;
	}
	public String getArticleTransportFile() {
		return articleTransportFile;
	}
	public void setArticleTransportFile(String articleTransportFile) {
		if(articleTransportFile==null) articleTransportFile = "";
		this.articleTransportFile = articleTransportFile;
	}
	
	@Override
	public String toString() {
		return reservationId; 
	}
	
	@Override
	public int hashCode() {
		return reservationId.hashCode(); 
	}
	
	@Override
	public Reservation clone() {
		Reservation reservation = new Reservation();
		reservation.articleDescription = articleDescription; 
		reservation.articleTransportFile = articleTransportFile; 
		reservation.flyId = flyId;
		reservation.placeCount = placeCount;
		reservation.reservationId = reservationId; 
		reservation.username = username;
		return reservation;
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof Reservation) {
			Reservation reservation = new Reservation();
			return reservationId.contentEquals(reservation.reservationId); 
		}
		return false; 
	}
	
	@Override
	public int compareTo(Reservation o) {
		return reservationId.compareTo(o.reservationId);
	}
}
