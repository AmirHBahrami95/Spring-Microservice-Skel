package com.XX_APP_NAME.usr;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.XX_APP_NAME.usr.model.BusinessLogicException;

@ControllerAdvice
public class ExcHandler {
		
	@ExceptionHandler({org.springframework.dao.DataIntegrityViolationException.class})
	public ResponseEntity<Map<String,String>> handleDataIntegrity(org.springframework.dao.DataIntegrityViolationException exc) {
		Map<String,String> errCause=new HashMap<>();
		errCause.put("message", exc.getMessage());
		errCause.put("err", "Duplicate Entry");
		return ResponseEntity.badRequest().body(errCause);
	}
	
	@ExceptionHandler({BusinessLogicException.class})
	public ResponseEntity<Map<String,String>> handle(BusinessLogicException ble){
		Map<String,String> res=new HashMap<>();
		res.put("error", ble.getUserMsg());
		return ResponseEntity.status(ble.getStatusCode()).body(res);
	}

}
