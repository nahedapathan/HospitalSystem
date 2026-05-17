package com.hms.HospitalManamentSystem.controller;

import org.springframework.web.bind.annotation.RestController;

import com.hms.HospitalManamentSystem.dto.LoginRequestDto;
import com.hms.HospitalManamentSystem.dto.LoginResponseDto;
import com.hms.HospitalManamentSystem.dto.SignupResponseDto;
import com.hms.HospitalManamentSystem.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto)
	{
		return ResponseEntity.ok(authService.login(loginRequestDto));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<SignupResponseDto> signup(@RequestBody LoginRequestDto signupRequestDto)
	{
		return ResponseEntity.ok(authService.signup(signupRequestDto));
	}
}
