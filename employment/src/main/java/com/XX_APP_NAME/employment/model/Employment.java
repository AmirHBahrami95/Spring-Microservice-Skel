package com.XX_APP_NAME.employment.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity 
@Data @NoArgsConstructor @AllArgsConstructor 
@Builder @ToString
public class Employment {
	
	@Id @GeneratedValue(strategy=GenerationType.UUID)
	private UUID id;
	
	@Column(nullable=false)
	private UUID userId;
	
	@Column(nullable=false)
	private UUID orgId;
	
	@Column(nullable=false)
	private String role;
	
	@CreatedDate
	private LocalDateTime entryDate;

	// TODO keeping a history of employments as a form of db tables should rather exist
	// for instance what if an employee moves back and forth to different departments?!
	// we're fucked!
	
	// plus if you constraint everything together, then you cannot add consecutive 
	// transfers between different fuckeries (AND update the date and all that)
	
	@Column(nullable=true)
	private LocalDateTime lastWorkingDate;
	
	@LastModifiedDate
	private LocalDateTime lastUpdated;
	
	@Builder.Default
	@Column
	private boolean isActive=true;

}
