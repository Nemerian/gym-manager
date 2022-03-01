package com.radu.repository;

import java.util.List;

import com.radu.model.Istoric;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Istoric_Repository extends JpaRepository<Istoric, Long> {
	
	@Query(value = "SELECT * FROM Istoric WHERE idmembru = ?1", nativeQuery = true)
	List<Istoric> findByIdMembru(Long IdMembru);
	
	@Query(value = "SELECT * FROM Istoric WHERE idmembru = ?1 ORDER BY datastart DESC limit 1", nativeQuery = true)
	Istoric findLastByIdMembru(Long idmembru);
}