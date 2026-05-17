package com.hms.HospitalManamentSystem.service;

import org.springframework.stereotype.Service;

import com.hms.HospitalManamentSystem.entity.Insurance;
import com.hms.HospitalManamentSystem.entity.Patient;
import com.hms.HospitalManamentSystem.repository.InsuranceRepository;
import com.hms.HospitalManamentSystem.repository.PatientRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InsuranceService {

	private final InsuranceRepository insuranceRepository;
	private final PatientRepository patientRepository;
	
	@Transactional
	public Patient assignInsuranceToPatient(Insurance insurance,Long patientId)
	{
		Patient patient=patientRepository.findById(patientId)
				                         .orElseThrow(()->new EntityNotFoundException("Patient not found with id:"+patientId));
		patient.setInsurance(insurance);
		insurance.setPatient(patient);
		return patient;
	}
	
	@Transactional
	public Patient dissociateInsuranceFromPatient(Long patientId)
	{
		Patient patient=patientRepository.findById(patientId)
				                           .orElseThrow(()->new EntityNotFoundException("Patient not found with id: "+patientId));
		patient.setInsurance(null);
		return patient;
	}
}
