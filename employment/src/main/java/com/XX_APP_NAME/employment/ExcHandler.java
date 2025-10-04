package com.XX_APP_NAME.employment;

import java.util.concurrent.TimeoutException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExcHandler {
	
	// TODO log what goes wrong, if it goes wrong enough times!
	
	/**
	 * Because fucking Bulk fucking head requires a "CompletableFuture"
	 * (like js futures) , you're gonna run into loads of stupid problems
	 * so just handle the fucker here (FFFS).
	 * */
	@ExceptionHandler({TimeoutException.class})
	@ResponseStatus(value = HttpStatus.REQUEST_TIMEOUT)
	public void handleTimeoutException() {}


}
