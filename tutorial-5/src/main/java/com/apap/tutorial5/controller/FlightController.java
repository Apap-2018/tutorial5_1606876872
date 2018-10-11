package com.apap.tutorial5.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		ArrayList<FlightModel> list = new ArrayList<FlightModel>();
		list.add(new FlightModel());
		pilot.setPilotFlight(list);
		
		model.addAttribute("pilot", pilot);
		model.addAttribute("title", "Add Flight");
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.POST, params= {"save"})
	private String addFlightSubmit(@ModelAttribute PilotModel pilot) {
		PilotModel curr_pilot = pilotService.getPilotDetailByLicenseNumber(pilot.getLicenseNumber());
		
		for (FlightModel flight : pilot.getPilotFlight()) {
			flight.setPilot(curr_pilot);
			flightService.addFlight(flight);;
		}
		return "add";
	}
	
	@RequestMapping(value = "/flight/add/{id}", params= {"addRow"}, method = RequestMethod.POST)
	private String addRow (@ModelAttribute PilotModel pilot, Model model) {
		pilot.getPilotFlight().add(new FlightModel());
		
		model.addAttribute("pilot", pilot);
		return "addFlight";
	}
	
	@RequestMapping(value="/flight/add/{id}", method = RequestMethod.POST, params={"removeRow"})
	private String removeRow (@ModelAttribute PilotModel pilot, final BindingResult bindingResult, final HttpServletRequest req, Model model) {
		final Integer row = Integer.valueOf(req.getParameter("removeRow"));
		pilot.getPilotFlight().remove(row.intValue());
		
		model.addAttribute("pilot", pilot);
		return "addFlight";
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
	
/*	@RequestMapping(value ="/flight/viewall{flightNumber}", method = RequestMethod.GET)
	private String viewAllFlight(@PathVariable(value = "flightNumber") String flightNumber, Model model) {
		FlightModel flightModel = flightService.getFlightDetailByFlightNumber(flightNumber);
		model.addAttribute("flightNumber", flightModel.getFlightNumber());
		model.addAttribute("pilot", flightModel.getPilot());
		model.addAttribute("title", "Viewall Flight");
		return "viewall";
	}*/
	
	
	
}
