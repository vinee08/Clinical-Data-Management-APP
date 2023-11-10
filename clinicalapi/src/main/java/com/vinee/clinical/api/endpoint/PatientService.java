package com.vinee.clinical.api.endpoint;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.springframework.beans.factory.annotation.Autowired;

import com.vinee.clinical.api.model.ClinicalData;
import com.vinee.clinical.api.model.Patient;
import com.vinee.clinical.api.repo.PatientRepo;


@Consumes("application/json")
@Produces("application/json")
@Path("/api")  
@CrossOriginResourceSharing(allowAllOrigins=true)
public class PatientService {
      
	@Autowired
	PatientRepo repo;
	
	@Path("/patients") 
	@POST
	public Patient createPatient(Patient patient) {
		     return repo.save(patient);
	   } 
	@Path("/patient/{id}") 
	@GET
	public Patient getpatient(@PathParam("id")int id) {
		return repo.findById(id).get();
	}
	@Path("/patients") 
	@GET
	public List<Patient> getPatient(){
		return repo.findAll();
	} 
	@Path("/patients/analyze/{id}") 
	@GET
	public Patient analyze(@PathParam("id")int id) {
		Patient patient = repo.findById(id).get(); 
		List<ClinicalData> clinicalData = new ArrayList<>(patient.getClinicalData());
		for(ClinicalData eachEntry:clinicalData) {
		      if(eachEntry.getComponentName().equals("hw")) {
			        String[] heightAndWeight = eachEntry.getComponentValue().split("/");
		            String height = heightAndWeight[0];
		            String weight = heightAndWeight[1]; 
		            
		            float heightInMeters = Float.parseFloat(height)* 0.4536F;	
		            Float bmi = Float.parseFloat(weight)/(heightInMeters*heightInMeters); 
		            ClinicalData bmidata = new ClinicalData(); 
		            bmidata.setComponentName("bmi");
		            bmidata.setComponentValue(bmi.toString()); 
		            patient.getClinicalData().add(bmidata);
		}
		}
		return patient;
		
	}
}
