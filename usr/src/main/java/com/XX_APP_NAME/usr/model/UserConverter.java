package com.XX_APP_NAME.usr.model;

public class UserConverter {
	
	public User ofDto(UserRequestDto urd) {
		return User.builder()
			.email(urd.getEmail())
			.fname(urd.getFname())
			.lname(urd.getLname())
			.address(urd.getAddress())
			.phoneNo(urd.getPhoneNo())
			.build();
	}
	
	public UserResponseDto toDto(User u) {
		return UserResponseDto.builder()
			.id(u.getId())
			.email(u.getEmail())
			.fname(u.getFname())
			.lname(u.getLname())
			.build();
	}

}
