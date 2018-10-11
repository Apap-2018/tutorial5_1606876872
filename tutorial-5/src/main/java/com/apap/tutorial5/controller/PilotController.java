package com.apap.tutorial5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.PilotService;

@Controller
public class PilotController {

	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new PilotModel());
		model.addAttribute("title", "Add Pilot");
		return "addPilot";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot, Model model) {
		pilotService.addPilot(pilot);
		model.addAttribute("title", "Status");
		return "add";
	}
	
	@RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
	private String viewPilot(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel pilots = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		
		model.addAttribute("pilot", pilots);
		model.addAttribute("title", "View Pilot");
		return "view-pilot";
//		if (pilots==null) {
//			model.addAttribute("licenseNum", licenseNumber);
//			return "salah";
//		}else {
//			model.addAttribute("pilot", pilots);
//			model.addAttribute("flightList", pilots.getPilotFlight());
//			return "view-pilot";
//		}	
	}
	
/*	@RequestMapping(value = "/pilot/delete/{licenseNumber}", method = RequestMethod.GET)
	private String deletePilot(@PathVariable( value = "licenseNumber") String licenseNumber, Model model) {
		pilotService.deletePilot(licenseNumber);
		return "delete-pilot";
		
	}*/
	
	@RequestMapping(value = "/pilot/delete/{id}", method = RequestMethod.GET)
	private String deletePilot(@PathVariable( value = "id") long id, Model model) {
		pilotService.deletePilotById(id);
		model.addAttribute("title", "Delete Pilot");
		return "delete-pilot";
	}

	
/*	@RequestMapping(value = "/pilot/delete", method = RequestMethod.GET)
	private String deletePilot(@RequestParam("pilotId") long id, Model model) {
		PilotModel pilot = pilotService.getPilotDetailById(id);
		pilotService.deletePilot(pilot);
		model.addAttribute("title", " Delete Pilot");
		return "delete-pilot";
	}*/
	
	@RequestMapping(value = "/pilot/update{licenseNumber}", method = RequestMethod.GET)
	private String update(@PathVariable(value= "licenseNumber") String licenseNumber, Model model) {
		PilotModel oldPilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("oldPilot", oldPilot);
		model.addAttribute("newPilot", new PilotModel());
		model.addAttribute("title", "Update Pilot");
		return "update-pilot";
	}
	
	@RequestMapping(value = "/pilot/update{licenseNumber}", method = RequestMethod.POST)
	private String updatePilotSubmit(@ModelAttribute PilotModel newPilot, @PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		pilotService.updatePilot(licenseNumber, newPilot);
		model.addAttribute("title", "Update Pilot");
		return "update";
	}
	
}
 