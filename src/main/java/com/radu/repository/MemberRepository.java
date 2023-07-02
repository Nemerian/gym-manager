package com.radu.repository;

import java.time.LocalDate;
import java.util.List;

import com.radu.model.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByDateEndBefore(LocalDate now);
}