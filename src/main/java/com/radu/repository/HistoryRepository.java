package com.radu.repository;

import java.util.List;

import com.radu.model.History;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HistoryRepository extends JpaRepository<History, Long> {
	
    @Query(value = "SELECT * FROM history WHERE id_member = ?1", nativeQuery = true)
    List<History> findByIdmember(Long idmember);
    
    @Query(value = "SELECT * FROM history WHERE id_member = ?1 ORDER BY date_start DESC limit 1", nativeQuery = true)
    History findFirstByidmemberOrderByDatainceputDesc(Long idmember);
}