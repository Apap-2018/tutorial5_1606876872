package com.apap.tutorial5.service;

import java.util.List;

import com.apap.tutorial5.model.PilotModel;

public interface PilotService {
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	void deletePilotById(long id);
	List <PilotModel> getListPilot();
	PilotModel getPilotDetailById(long id);
	void addPilot(PilotModel pilot);
	void updatePilot(String licenseNumber, PilotModel pilot);
	
	
}
