package com.hms.HospitalManamentSystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.HospitalManamentSystem.dto.AppointmentResponseDto;
import com.hms.HospitalManamentSystem.dto.CreateAppointmentRequestDto;
import com.hms.HospitalManamentSystem.dto.PatientResponseDto;
import com.hms.HospitalManamentSystem.service.AppointmentService;
import com.hms.HospitalManamentSystem.service.PatientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

	private final PatientService patientService;
	private final AppointmentService appointmentService;
	
	@PostMapping("/appointments")
	public ResponseEntity<AppointmentResponseDto> createNewAppointment(@RequestBody CreateAppointmentRequestDto createAppointmentRequestDto)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createNewAppointment(createAppointmentRequestDto));
	}
	
	@GetMapping("/profile")
	public ResponseEntity<PatientResponseDto> getPatientProfile()
	{
		Long patientId=4L;
		return ResponseEntity.ok(patientService.getPatientById(patientId));
	}
}
