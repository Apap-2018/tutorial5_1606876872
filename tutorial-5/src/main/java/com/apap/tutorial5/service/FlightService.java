package com.apap.tutorial5.service;

import com.apap.tutorial5.model.FlightModel;

public interface FlightService {
	FlightModel getFlightDetailByFlightNumber(String flightNumber);
	void addFlight(FlightModel flight);
/*    void deleteFlight(String flightNumber);*/
    void deleteFlightById(long flightId);
	void updateFlight(String flightNumber, FlightModel flight);
}