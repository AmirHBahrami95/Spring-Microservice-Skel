package com.XX_APP_NAME.employment.model;

import org.springframework.stereotype.Component;

import com.XX_APP_NAME.employment.model.dto.EmploymentRequestDto;
import com.XX_APP_NAME.employment.model.dto.EmploymentResponseDto;
import com.XX_APP_NAME.employment.model.dto.UserResponseDto;

@Component
public class EmploymentConverter {
	
	public Employment ofDto(EmploymentRequestDto dto) {
		return Employment.builder()
			.id(dto.getId())
			.userId(dto.getUserId())
			.orgId(dto.getOrgId())
			.role(dto.getRole())
			.build();
	}
	
	public EmploymentResponseDto toDto(Employment e) {
		return toDto(e,null);
	}
	
	public EmploymentResponseDto toDto(Employment e, UserResponseDto userDto /*, OrgResponseDto orgDto*/ ) {
		return EmploymentResponseDto.builder()
		.id(e.getId())
		.lastUpdated(e.getLastUpdated())
		.lastWorkingDate(e.getLastWorkingDate())
		.entryDate(e.getEntryDate())
		.role(e.getRole())
		.user(
			userDto!=null?userDto
			:UserResponseDto.builder().id(e.getUserId()).build() // set a dto only with an id field
		)
		.build();
	}
	
}
