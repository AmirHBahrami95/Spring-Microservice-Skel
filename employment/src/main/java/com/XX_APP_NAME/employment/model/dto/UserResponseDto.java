package com.XX_APP_NAME.employment.model.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserResponseDto {
	
	@Builder.Default
	private long version=1l;
	
	private UUID id;
	private String email;
	private String fname;
	private String lname;
}
