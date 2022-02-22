package com.radu.repository;

import java.util.List;

import com.radu.model.Istoric_Abonat;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Istoric_Abonat_Repository extends JpaRepository<Istoric_Abonat, Long> {

	List<Istoric_Abonat> findAll();
}