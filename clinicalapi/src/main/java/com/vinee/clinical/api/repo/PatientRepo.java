package com.vinee.clinical.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinee.clinical.api.model.Patient;

public interface PatientRepo extends JpaRepository<Patient, Integer> {

}
