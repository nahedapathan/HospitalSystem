package com.hms.HospitalManamentSystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hms.HospitalManamentSystem.dto.DoctorResponseDto;
import com.hms.HospitalManamentSystem.repository.DoctorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorService {

	private final DoctorRepository doctorRepository;
	private final ModelMapper modelMapper;
	
	public List<DoctorResponseDto> getAllDoctor()
	{
		return doctorRepository.findAll()
				               .stream()
				               .map(doctor->modelMapper.map(doctor, DoctorResponseDto.class))
				               .collect(Collectors.toList());
	}
}
