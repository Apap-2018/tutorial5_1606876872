package com.apap.tutorial5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.repository.FlightDb;
import com.apap.tutorial5.service.FlightService;

@Service
@Transactional
public class FlightServiceImpl implements FlightService{
	@Autowired
	private FlightDb flightDb;
	
	@Override
	public void addFlight(FlightModel flight) {
		flightDb.save(flight);
	}
	
/*	@Override
	public void deleteFlight (String flightNumber) {
		flightDb.delete(this.getFlightDetailByFlightNumber(flightNumber));
	}*/
	
	@Override
	public FlightModel getFlightDetailByFlightNumber(String flightNumber) {
		return flightDb.findByFlightNumber(flightNumber);
	}

	
	@Override
	public void deleteFlightById (long id) {
		flightDb.deleteById(id);
	}
		
	@Override
	public void updateFlight (String flightNumber, FlightModel newFlight) {
		FlightModel oldFlight = this.getFlightDetailByFlightNumber(flightNumber);
		oldFlight.setFlightNumber(newFlight.getFlightNumber());
		oldFlight.setOrigin(newFlight.getOrigin());
		oldFlight.setDestination(newFlight.getDestination());
		oldFlight.setTime(newFlight.getTime());
	}
}