package com.hms.HospitalManamentSystem.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hms.HospitalManamentSystem.dto.LoginRequestDto;
import com.hms.HospitalManamentSystem.dto.LoginResponseDto;
import com.hms.HospitalManamentSystem.dto.SignupResponseDto;
import com.hms.HospitalManamentSystem.entity.User;
import com.hms.HospitalManamentSystem.repository.UserRepository;
import com.hms.HospitalManamentSystem.security.AuthUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
 
	private final AuthenticationManager authenticationManager;
	private final AuthUtil authUtil;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public LoginResponseDto login(LoginRequestDto loginRequestDto)
	{
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword()));
	
		User user=(User) authentication.getPrincipal();
		String token=authUtil.generateAccessToken(user);
		return new LoginResponseDto(token,user.getId());
	}
	
	public SignupResponseDto signup(LoginRequestDto signupRequestDto)
	{
		User user=userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);
		
		if(user!=null) throw new IllegalArgumentException("User Already Exists !!!");
		
		user=userRepository.save(User.builder()
				           .username(signupRequestDto.getUsername())
				           .password(passwordEncoder.encode(signupRequestDto.getPassword()))
				           .build()
				);
		return new SignupResponseDto(user.getId(),user.getUsername());
		
	}
}

