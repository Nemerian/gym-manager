package com.radu.repository;

import java.util.List;

import com.radu.model.Istoric_Abonat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Istoric_Abonat_Repository extends JpaRepository<Istoric_Abonat, Long> {
	List<Istoric_Abonat> findAll();
	
	@Query(value = "SELECT * FROM Istoric_Abonat WHERE IdMembru = ?1", nativeQuery = true)
	List<Istoric_Abonat> findByIdMembru(Long IdMembru);

}