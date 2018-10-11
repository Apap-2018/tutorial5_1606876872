package com.apap.tutorial5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.repository.PilotDb;
import com.apap.tutorial5.service.PilotService;

@Service
@Transactional
public class PilotServiceImpl implements PilotService{
	@Autowired
	private PilotDb pilotDb;
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDb.findByLicenseNumber(licenseNumber);
	}
	
	@Override
	public PilotModel getPilotDetailById(long id) {
		return pilotDb.findById(id);
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		pilotDb.save(pilot);
	}
	
/*	@Override
	public void deletePilot (String licenseNumber) {
		pilotDb.delete(this.getPilotDetailByLicenseNumber(licenseNumber));
	}*/
	
	@Override
	public void deletePilotById (long id) {
		pilotDb.deleteById(id);
	}
	
/*	@Override
	public void deletePilot(PilotModel pilot) {
		pilotDb.delete(pilot);
	}
	*/
	@Override
	public void updatePilot (String licenseNumber, PilotModel newPilot) {
		PilotModel oldPilot = this.getPilotDetailByLicenseNumber(licenseNumber);
		oldPilot.setName(newPilot.getName());
		oldPilot.setFlyHour(newPilot.getFlyHour());
	}


}
