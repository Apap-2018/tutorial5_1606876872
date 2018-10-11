package com.apap.tutorial5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.FlightService;
import com.apap.tutorial5.service.PilotService;

@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		
		model.addAttribute("flight", flight);
		model.addAttribute("title", "Add Flight");
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight, Model model) {
		model.addAttribute("title", "Status");
		flightService.addFlight(flight);
		return "add";
	}
	
	
	@RequestMapping(value = "/flight/delete", method = RequestMethod.POST)
    private String deleteFlight(@ModelAttribute PilotModel pilot, Model model) {
		for(FlightModel flight : pilot.getPilotFlight()) {
			flightService.deleteFlightById(flight.getId());
		}
		model.addAttribute("title", "Delete Flight");
		return "delete-flight";
	}
	
	@RequestMapping(value = "/flight/update{flightNumber}", method = RequestMethod.GET)
	private String updateF(@PathVariable(value= "flightNumber") String flightNumber, Model model) {
		FlightModel oldFlight = flightService.getFlightDetailByFlightNumber(flightNumber);
		model.addAttribute("oldFlight", oldFlight);
		model.addAttribute("newFlight", new FlightModel());
		model.addAttribute("title", "Update Flight");
		return "update-flight";
	}
	
	@RequestMapping(value = "/flight/update{flightNumber}", method = RequestMethod.POST)
	private String updateFlightSubmit(@ModelAttribute FlightModel newFlight, @PathVariable(value = "flightNumber") String flightNumber, Model model) {
		flightService.updateFlight(flightNumber, newFlight);
		model.addAttribute("title", "Update Flight");
		return "update";
	}
	
	@RequestMapping(value ="/flight/viewall{flightNumber}", method = RequestMethod.GET)
	private String viewAllFlight(@PathVariable(value = "flightNumber") String flightNumber, Model model) {
		FlightModel flightModel = flightService.getFlightDetailByFlightNumber(flightNumber);
		model.addAttribute("flightNumber", flightModel.getFlightNumber());
		model.addAttribute("pilot", flightModel.getPilot());
		model.addAttribute("title", "Viewall Flight");
		return "viewall";
	}
	
	
	
}
