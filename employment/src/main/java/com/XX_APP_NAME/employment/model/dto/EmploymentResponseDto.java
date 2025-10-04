package com.XX_APP_NAME.employment.model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class EmploymentResponseDto {
	
	@Builder.Default
	private long version=1l;
	
	private UUID id;
	private UserResponseDto user;
	// private OrgResponseDto org;
	private String role;
	private LocalDateTime entryDate;
	private LocalDateTime lastWorkingDate;
	private LocalDateTime lastUpdated;
}
