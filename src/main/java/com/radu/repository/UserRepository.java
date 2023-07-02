package com.radu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.radu.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findById(Integer id);

    Optional<User> findByUsername(String username);
}