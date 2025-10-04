package com.XX_APP_NAME.employment.model.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class EmploymentRequestDto {
	
	@Builder.Default
	private long version=1l;
	
	private UUID id;
	private UUID userId;
	private UUID orgId;
	private String role;
}
