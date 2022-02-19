package com.radu.repository;

import java.util.List;

import com.radu.model.Istoric_Abonati;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Istoric_Abonati_Repository extends JpaRepository<Istoric_Abonati, Long> {

	List<Istoric_Abonati> findAll();
}