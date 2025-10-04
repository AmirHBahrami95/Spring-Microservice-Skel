package com.XX_APP_NAME.usr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Getter @Setter
public class BusinessLogicException extends RuntimeException{
	
	private int statusCode;
	private String userMsg;
}
