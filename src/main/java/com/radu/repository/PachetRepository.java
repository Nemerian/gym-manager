package com.radu.repository;

import java.util.List;

import com.radu.model.Pachet;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PachetRepository extends JpaRepository<Pachet, Long> {

	List<Pachet> findAll();
}