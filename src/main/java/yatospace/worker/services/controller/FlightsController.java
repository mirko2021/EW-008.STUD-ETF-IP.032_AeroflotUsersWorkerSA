package yatospace.worker.services.controller;

import yatospace.worker.services.io.FligthsDAO;

/**
 * Контролер који се односи на податке о летовима. 
 * @author VM
 * @version 1.0
 */
public class FlightsController {
	private FligthsDAO flightsDAO;

	public FlightsController(FligthsDAO flightsDAO) {
		if(flightsDAO == null) throw new NullPointerException(); 
		this.flightsDAO = flightsDAO; 
	}
	
	public FligthsDAO getFlightsDAO() {
		return flightsDAO;
	}
}
