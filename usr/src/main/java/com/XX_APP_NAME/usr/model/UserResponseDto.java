package com.XX_APP_NAME.usr.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserResponseDto {
	
	private UUID id;
	private String email;
	private String fname;
	private String lname;

}
