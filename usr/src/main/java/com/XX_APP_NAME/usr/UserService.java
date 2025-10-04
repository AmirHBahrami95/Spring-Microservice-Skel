package com.XX_APP_NAME.usr;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.XX_APP_NAME.usr.model.BusinessLogicException;
import com.XX_APP_NAME.usr.model.User;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	public Boolean checkIfExists(UUID id) {
		Optional<User> queried=userRepo.findById(id);
		return queried.isPresent();
	}
	
	public Optional<User> findById(UUID id){
		return userRepo.findById(id);
	}

	@Transactional
	public Optional<User> add(User u) {
		
		// avoid updating an existing user
		if(u.getId()!=null || u.getEmail().isBlank() || u.getPhoneNo().isBlank()) 
			throw new BusinessLogicException(400,"err.general.bad_request");
		
		// user exists already
		if(userRepo.findByEmail(u.getEmail()).isPresent())
			throw new BusinessLogicException(400, "error.user.duplicate_email");
		
		if(userRepo.findByPhoneNo(u.getPhoneNo()).isPresent())
			throw new BusinessLogicException(400,"error.user.duplicate_phone_no");
		
		User saved=userRepo.save(u);
		return saved.getId()==null?
			Optional.empty():
			Optional.of(saved);
	}
	
	// TODO public Optional<User> update(User u){...}

}
