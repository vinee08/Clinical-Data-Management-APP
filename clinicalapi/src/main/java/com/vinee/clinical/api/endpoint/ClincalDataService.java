package com.vinee.clinical.api.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.springframework.beans.factory.annotation.Autowired;

import com.vinee.clinical.api.dto.ClinicalDataRequest;
import com.vinee.clinical.api.model.ClinicalData;
import com.vinee.clinical.api.model.Patient;
import com.vinee.clinical.api.repo.ClinicalDatarepo;
import com.vinee.clinical.api.repo.PatientRepo;

@Consumes("application/json")
@Produces("application/json")
@Path("/api")
@CrossOriginResourceSharing(allowAllOrigins=true)
public class ClincalDataService {
	@Autowired
	PatientRepo patientRepo; 
	@Autowired
	ClinicalDatarepo clinicalDataRepo; 
	@Path("/clinicals") 
	@POST
	public ClinicalData saveClinicalData(ClinicalDataRequest request) {
		Patient patient = patientRepo.findById(request.getPatientId()).get();
        ClinicalData clinicalData = new ClinicalData();
		clinicalData.setPatient(patient); 
        clinicalData.setComponentName(request.getComponentName());
        clinicalData.setComponentValue(request.getComponentValue());
		return clinicalDataRepo.save(clinicalData);
		
	}
}
