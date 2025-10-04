package com.XX_APP_NAME.employment;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.XX_APP_NAME.employment.model.EmploymentConverter;
import com.XX_APP_NAME.employment.model.dto.EmploymentRequestDto;
import com.XX_APP_NAME.employment.model.dto.EmploymentResponseDto;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/")
public class EmploymentController {
	
	@Autowired 
	private EmploymentService employmentService;
	
	@Autowired 
	private EmploymentConverter employmentConverter;

	@PostMapping(path="/add", consumes="application/json", produces="application/json")
	@RateLimiter(name="employmentControllerRateLimiter")
	@Retry(name="employmentControllerRetry",fallbackMethod = "add500")
	@Bulkhead(name="employmentControllerBulkhead",type = Bulkhead.Type.THREADPOOL)
	@CircuitBreaker(name="employmentControllerCircuitBreaker",fallbackMethod = "add500")
	public ResponseEntity<EmploymentResponseDto> add(
		@RequestBody(required=true) EmploymentRequestDto reqDto,
		@RequestParam(name = "expand_user",defaultValue = "0") String expandUser)
	{
		// log.error("I AM GROOT, BIATCH!");
		Optional<EmploymentResponseDto> saved=
			employmentService.add(
				employmentConverter.ofDto(reqDto),
				expandUser.equals("1"));
		if(saved.isEmpty()) return ResponseEntity.badRequest().build(); // it's on you buddy!
		return ResponseEntity.ok(saved.get());
	}
	
	
	@GetMapping(path="/{id}" , produces="application/json")
	@Retry(name="employmentControllerRetry",fallbackMethod = "getById500")
	@Bulkhead(name="employmentControllerBulkhead",type = Bulkhead.Type.THREADPOOL)
	@CircuitBreaker(name="employmentControllerCircuitBreaker",fallbackMethod = "getById500")
	public ResponseEntity<EmploymentResponseDto> getById(
		@PathVariable(required=true) UUID id,
		@RequestParam (name="expand_user",defaultValue="0") String expandUser)
	{
		Optional<EmploymentResponseDto> found=
			employmentService.getById(id, expandUser.equals("1"));
		
		return // CompletableFuture.completedFuture(
			found.isPresent()?
			ResponseEntity.ok(found.get()):
			ResponseEntity.notFound().build()
		//)
		;
	}
	
	// -------------------------------------------------- FALLBACKS
	
	public ResponseEntity<EmploymentResponseDto> add500(EmploymentRequestDto reqDto,  String expandUser, Throwable t){
		t.printStackTrace(); // TODO replace with somethign solid
		return ResponseEntity.internalServerError().build();
	}
	
	public ResponseEntity<EmploymentResponseDto> getById500(UUID id,String expandUser,Throwable t){
		return //CompletableFuture.completedFuture(
				ResponseEntity.internalServerError().build()
		//)
		;
	}
	
}