package com.XX_APP_NAME.employment.clients;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.XX_APP_NAME.employment.model.dto.UserResponseDto;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserClient{ // only works as a bridge, should NOT implement any business logic 
	
	@Autowired
	private RestTemplate restTemplate;
	
	/*
	 * XXX
	 * don't put these here, bc there can be multiple clients in one controller
	 * and that will add bulkhead overhead and makes retry and ratelimit a bit too 
	 * fucked up
	 * XXX
	@Retry(name="userClientRetry",fallbackMethod = "getById500")
	@Bulkhead(name="userClientBulkhead", fallbackMethod = "getById500")
	@CircuitBreaker(name="userClientCircuitBreaker",fallbackMethod = "getById500")
	*/
	public ResponseEntity<UserResponseDto> getById(UUID userId){
		return restTemplate.exchange(
			"http://usr/{id}",
			HttpMethod.GET,
			null,
			UserResponseDto.class,
			userId
		);
	}
	
	// -------------------------------------------------- FALLBACKS
	
	public ResponseEntity<UserResponseDto> getById500(UUID userId){
		log.warn("UserClient.getById() failed - calling fallback");
		return ResponseEntity.internalServerError().build();
	}
	
}
