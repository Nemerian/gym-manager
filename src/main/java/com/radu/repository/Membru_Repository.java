package com.radu.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.radu.model.Istoric;
import com.radu.model.Membru;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Membru_Repository extends JpaRepository<Membru, Long> {

	LocalDateTime now = LocalDateTime.now();  
	
	//@Query(value = "SELECT * FROM Istoric WHERE idmembru = ?1", nativeQuery = true)
	//@Query("SELECT * FROM Email e where e.active = true and e.expire <= NOW()")
	//@Query("UPDATE membru FROM istoric SET price = :price WHERE name = :name")
	//List<Membru> UpdateActivPachet(List<Membru> membri);

	
}