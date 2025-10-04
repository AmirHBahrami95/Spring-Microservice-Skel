package com.XX_APP_NAME.usr;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.XX_APP_NAME.usr.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,UUID>{
	Optional<User> findById(UUID id);
	User save(User u); // returns with id
	
	// find by unique's, for better reporting duplicate errors to user 
	Optional<User> findByEmail(String email);
	Optional<User> findByPhoneNo(String phoneNo);
	
	@Query("SELECT u FROM User u WHERE "
		+ "( :address IS NULL OR u.address LIKE CONCAT('%', :address ,'%'))"
		+ "AND ( :fname IS NULL OR u.fname LIKE  CONCAT('%', :fname ,'%')) "
		+ "AND ( :lname IS NULL OR u.lname LIKE CONCAT('%', :lname ,'%'))"
		+ "AND ( :email IS NULL OR u.email LIKE CONCAT('%', :email ,'%'))"
		+ "AND ( :isActive IS NULL OR u.isActive=:isActive)" // this is... weird
	)
	List<User> search(
		@Param("fname") String fname,
		@Param("lname") String lname,
		@Param("email") String email,
		@Param("address") String address,
		@Param("isActive") Boolean isActive
	);
	
	// TODO another search with boolean wank
	
	@Query("UPDATE User u SET u.isActive=false WHERE u.id=id")
	void del(@Param("id")UUID id);
}
