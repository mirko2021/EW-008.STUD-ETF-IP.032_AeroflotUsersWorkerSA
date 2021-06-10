package yatospace.worker.services.controller;

import yatospace.worker.services.io.ReservationsDAO;

/**
 * Контролер који се односи на резервације. 
 * @author VM
 * @version 1.0
 */
public class ReservationsController {
	private ReservationsDAO reservationsDAO;
	
	public ReservationsController(ReservationsDAO reservationsController) {
		if(reservationsController == null) throw new NullPointerException();
		this.reservationsDAO = reservationsController;
	}

	public ReservationsDAO getReservationsDAO() {
		return reservationsDAO;
	}
}
