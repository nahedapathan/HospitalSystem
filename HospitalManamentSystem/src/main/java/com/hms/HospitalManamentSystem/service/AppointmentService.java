package com.hms.HospitalManamentSystem.service;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hms.HospitalManamentSystem.dto.AppointmentResponseDto;
import com.hms.HospitalManamentSystem.dto.CreateAppointmentRequestDto;
import com.hms.HospitalManamentSystem.entity.Appointment;
import com.hms.HospitalManamentSystem.entity.Doctor;
import com.hms.HospitalManamentSystem.entity.Patient;
import com.hms.HospitalManamentSystem.repository.AppointmentRepository;
import com.hms.HospitalManamentSystem.repository.DoctorRepository;
import com.hms.HospitalManamentSystem.repository.PatientRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService {

	private final AppointmentRepository appointmentRepository;
	private final DoctorRepository doctorRepository;
	private final PatientRepository patientRepository;
	private final ModelMapper modelMapper;
	
	@Transactional
	public AppointmentResponseDto createNewAppointment(CreateAppointmentRequestDto createAppointmentRequestDto)
	{
		Long doctorId =createAppointmentRequestDto.getDoctorId();
		Long patientId=createAppointmentRequestDto.getPatientId();
		Patient patient=patientRepository.findById(patientId)
				                          .orElseThrow(()->new EntityNotFoundException("Patient not found with Id :"+patientId));
		
		Doctor doctor=doctorRepository.findById(doctorId)
				                        .orElseThrow(()->new EntityNotFoundException("Doctor not found with Id"+doctorId));
		Appointment appointment=Appointment.builder()
				                           .reason(createAppointmentRequestDto.getReason())
				                           .appointmentTime(createAppointmentRequestDto.getAppointmentTime())
				                           .build();
		appointment.setPatient(patient);
		appointment.setDoctor(doctor);
		patient.getAppointments().add(appointment);
		
		appointment=appointmentRepository.save(appointment);
		return modelMapper.map(appointment, AppointmentResponseDto.class);
	}
	
	@Transactional
	public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId,Long doctorId)
	{
		Appointment appointment=appointmentRepository.findById(appointmentId)
				                                    .orElseThrow();
		Doctor doctor=doctorRepository.findById(doctorId).orElseThrow();
		
		appointment.setDoctor(doctor);
		return appointmentRepository.save(appointment);
	}
	
	public List<AppointmentResponseDto> getAllAppointmentsOfDoctor(Long doctorId)
	{
		Doctor doctor=doctorRepository.findById(doctorId).orElseThrow();
		return doctor.getAppointments()
				   .stream()
				   .map(appointment -> modelMapper.map(appointment, AppointmentResponseDto.class))
				   .collect(Collectors.toList());
	}
}
