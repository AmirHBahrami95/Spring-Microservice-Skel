package com.XX_APP_NAME.employment;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.XX_APP_NAME.employment.clients.UserClient;
import com.XX_APP_NAME.employment.model.Employment;
import com.XX_APP_NAME.employment.model.EmploymentConverter;
import com.XX_APP_NAME.employment.model.dto.EmploymentResponseDto;
import com.XX_APP_NAME.employment.model.dto.UserResponseDto;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmploymentService {
	
	@Autowired 
	private EmploymentRepo employmentRepo;
	
	@Autowired 
	private EmploymentConverter employmentConverter;
	
	@Autowired
	private UserClient userClient;
	
	@Transactional
	public Optional<EmploymentResponseDto> getById(UUID id){
		return getById(id,false,false);
	}
	
	@Transactional
	public Optional<EmploymentResponseDto> getById(UUID id,boolean expandUser){
		return getById(id,expandUser,false);
	}
	
	// TODO if(user.isEmpty()) throw new 500 (this should be a logical error);
	@Transactional // TODO check if transactional is correct if you only read consecutively
	public Optional<EmploymentResponseDto> getById(UUID id,boolean expandUser,boolean expandOrg) {
		
		// check if employment is non-existent to stop consective operations
		Optional<Employment> found=employmentRepo.findById(id);
		if(found.isEmpty()) return Optional.empty();
		
		// builder-like behaviour here (bc of expansions)
		EmploymentResponseDto resDto=employmentConverter.toDto(found.get(), null);
		
		// expanding
		if(expandUser) {
			ResponseEntity<UserResponseDto> user=userClient.getById(found.get().getUserId());
			if(user.getStatusCode().isError()) throw new NotFoundException();
			resDto.setUser(user.getBody());
			
		}
		// TODO expand org
		
		return Optional.of(resDto);
	}
	
	@Transactional
	public Optional<EmploymentResponseDto> add(Employment e){
		return add(e,false,false);
	}
	
	@Transactional
	public Optional<EmploymentResponseDto> add(Employment e,boolean expandUser){
		return add(e,expandUser,false);
	}
	
	@Transactional
	public Optional<EmploymentResponseDto> add(Employment e,boolean expandUser, boolean expandOrg){
		
		// the first id checking is to separate add and update operations
		if(e.getId()!=null || e.getUserId()==null || e.getOrgId()==null) 
			return Optional.empty();
		
		// check user relations
		// TODO log the failed attempt
		ResponseEntity<UserResponseDto> user=userClient.getById(e.getUserId());
		if(user.getStatusCode().isError()) return Optional.empty();
		
		// TODO check org realtions
		
		// check if saved successfully
		Employment saved=employmentRepo.save(e);
		if(saved.getId()==null) return Optional.empty();
		
		return Optional.of(employmentConverter.toDto(
			saved,expandUser?user.getBody():null
		));
			
	}

}
