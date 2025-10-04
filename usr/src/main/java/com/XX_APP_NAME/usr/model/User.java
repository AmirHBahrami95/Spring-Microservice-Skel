package com.XX_APP_PASSWORD.usr.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity 
@EntityListeners(AuditingEntityListener.class)
@Data @NoArgsConstructor @AllArgsConstructor 
@Builder @ToString
public class User{
	
	// XXX not taking care of password and credentials YET
	// to save everything for future jackshit (oatuh2 & co)

	@Id @GeneratedValue(strategy=GenerationType.UUID)
	private UUID id;
	
	@Column(nullable=false,unique = true) // important especially for remote wankers (workers)
	private String email;
	
	@Column(nullable=false)
	private String fname;
	
	@Column(nullable=false)
	private String lname;

	@Column(nullable=true,unique = true)
	private String phoneNo;
	
	@Column(nullable=true)
	private String address;

	@CreatedDate
	private LocalDateTime createdAt=LocalDateTime.now();
	
	@LastModifiedDate
	private LocalDateTime lastUpdated=LocalDateTime.now();
	
	@Column
	@Builder.Default
	private boolean isActive=true;
	
	// TODO List<String> permissionGroups; // so that permission service can use it

}
