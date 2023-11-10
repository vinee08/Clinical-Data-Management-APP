package com.vinee.clinical.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinee.clinical.api.model.ClinicalData;

public interface ClinicalDatarepo extends JpaRepository<ClinicalData, Integer> {

}
