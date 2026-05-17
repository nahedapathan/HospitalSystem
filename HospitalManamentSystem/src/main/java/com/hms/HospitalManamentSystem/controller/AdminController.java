package com.hms.HospitalManamentSystem.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hms.HospitalManamentSystem.dto.PatientResponseDto;
import com.hms.HospitalManamentSystem.service.PatientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

	private final PatientService patientService;
	
	@GetMapping("/patients")
	public ResponseEntity<List<PatientResponseDto>> getAllPatients(
			@RequestParam(value="page",defaultValue = "0")Integer pageNumber,
			@RequestParam(value="size",defaultValue = "10")Integer pageSize)
	{
		return ResponseEntity.ok(patientService.getAllPatients(pageNumber, pageSize));
	}
}
