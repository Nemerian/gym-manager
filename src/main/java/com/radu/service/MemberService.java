package com.radu.service;
 
import com.radu.repository.MemberRepository;
import com.radu.exception.BadResourceException;
import com.radu.exception.ResourceAlreadyExistsException;
import com.radu.exception.ResourceNotFoundException;
import com.radu.model.Member;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
 
@Service
public class MemberService {

   
    @Autowired
    private MemberRepository memberRepository;
    
    private boolean existsById(Long id) {
        return memberRepository.existsById(id);
    }
    
    public Member findById(Long id) throws ResourceNotFoundException {
        Member member = memberRepository.findById(id).orElse(null);
        if (member==null) {
            throw new ResourceNotFoundException("Cannot find Contact with id: " + id);
        }
        else return member;
    }
    
    public List<Member> findAll(int pageNumber, int rowPerPage) {
        List<Member> members = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage, 
                Sort.by("id").ascending());
                memberRepository.findAll(sortedByIdAsc).forEach(members::add);
        return members;
    }
    
    public List<Member> findAll() {
        List<Member> member = new ArrayList<>();
        memberRepository.findAll().forEach(member::add);
        return member;
    }

    public Member save(Member member) throws BadResourceException, ResourceAlreadyExistsException {
        if (StringUtils.hasLength(member.getFamilyName())) {
            if (member.getId() != null && existsById(member.getId())) { 
                throw new ResourceAlreadyExistsException("member with id: " +  member.getId() +
                        " already exists");
            }
            return memberRepository.save(member);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save member");
            exc.addErrorMessage("Member is null or empty");
            throw exc;
        }
    }
    
    public void update(Member member) 
            throws BadResourceException, ResourceNotFoundException {
        if (StringUtils.hasLength(member.getFamilyName())) {
            if (!existsById(member.getId())) {
                throw new ResourceNotFoundException("Cannot find member with id: "+ member.getId());
            }
            memberRepository.save(member);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save member");
            exc.addErrorMessage("member is null or empty");
            throw exc;
        }
    }
    
    public void deleteById(Long id) throws ResourceNotFoundException  {
        if (!existsById(id)) { 
            throw new ResourceNotFoundException("Cannot find member with id: " + id);
        }
        else {
            memberRepository.deleteById(id);
        }
    }
    
    public Long count() {
        return memberRepository.count();
    }
}