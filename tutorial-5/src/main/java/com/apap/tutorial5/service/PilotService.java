package com.apap.tutorial5.service;

import com.apap.tutorial5.model.PilotModel;

public interface PilotService {
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
/*	void deletePilot(String licenseNumber);*/
	void deletePilotById(long id);
	/*void deletePilot(String licenseNumber);*/
	PilotModel getPilotDetailById(long id);
	void addPilot(PilotModel pilot);
	void updatePilot(String licenseNumber, PilotModel pilot);
	
	
}
