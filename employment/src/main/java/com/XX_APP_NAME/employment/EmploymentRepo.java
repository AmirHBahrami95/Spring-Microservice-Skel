package com.XX_APP_NAME.employment;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.XX_APP_NAME.employment.model.Employment;

public interface EmploymentRepo extends JpaRepository<Employment,UUID>{
	
	Optional<Employment> findById(UUID id);
	Optional<Employment> findByUserId(UUID userId);	
	
	// TODO search 
	
	Employment save(Employment e);
	
	// XXX there's a log pollution attack possible IF you just spam delete 
	// without checking if employee eixsts in the first place
	@Query("UPDATE Employment e SET e.isActive=false WHERE e.id=:id")
	void expire(@Param("id")UUID id);

}