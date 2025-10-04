package com.XX_APP_NAME.usr;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.XX_APP_NAME.usr.model.User;
import com.XX_APP_NAME.usr.model.UserConverter;
import com.XX_APP_NAME.usr.model.UserRequestDto;

@RestController
@RequestMapping(path = "/")
public class UserController {
	
    private Logger log = LoggerFactory.getLogger(UserController.class); 
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserConverter userDtoConverter;
	
	/*
		@GetMapping(path = "/exists/{id}",produces = "application/json")
		public ResponseEntity<Boolean> checkIfExists(UUID id) {
			return ResponseEntity.ok(userService.checkIfExists(id));
		}
	*/
	
	@GetMapping(path="/{id}",produces="application/json")
	public ResponseEntity<User> getById(@PathVariable(required=true) UUID id) {
		log.info("requested id:"+id.toString());
		Optional<User> retreived=userService.findById(id);
		return retreived.isPresent()?
			ResponseEntity.of(retreived):
			ResponseEntity.notFound().build();
	}
	
	@PostMapping(path="/add",produces="application/json",consumes="application/json")
	public ResponseEntity<User> addNew(@RequestBody(required = true) UserRequestDto userReq){
		Optional<User> u=userService.add(userDtoConverter.ofDto(userReq));
		if(u.isEmpty()) return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(u.get());
	}
	
}
