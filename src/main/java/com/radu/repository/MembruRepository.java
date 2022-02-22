package com.radu.repository;

import java.util.List;

import com.radu.model.Membru;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MembruRepository extends JpaRepository<Membru, Long> {

	List<Membru> findAll();
}