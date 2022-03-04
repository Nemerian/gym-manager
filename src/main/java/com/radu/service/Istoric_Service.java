package com.radu.service;

import com.radu.repository.Istoric_Repository;
import com.radu.exception.BadResourceException;
import com.radu.exception.ResourceAlreadyExistsException;
import com.radu.exception.ResourceNotFoundException;
import com.radu.model.Istoric;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class Istoric_Service {

   
    @Autowired
    private Istoric_Repository istoric_Repository;
    
    private boolean existsById(Long id) {
        return istoric_Repository.existsById(id);
    }
    
    public Istoric findById(Long id) throws ResourceNotFoundException {
        Istoric istoric = istoric_Repository.findById(id).orElse(null);
        if (istoric==null) {
            throw new ResourceNotFoundException("Cannot find Contact with id: " + id);
        }
        else return istoric;
    }

	public List<Istoric> findAll(int pageNumber, int rowPerPage) {
        List<Istoric> istoric = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage, 
                Sort.by("id").ascending());
                istoric_Repository.findAll(sortedByIdAsc).forEach(istoric::add);
        return istoric;
    }
    
    public Istoric save(Istoric istoric) throws BadResourceException, ResourceAlreadyExistsException {
        if (StringUtils.hasLength(istoric.getNumemembru())) {
            if (istoric.getId() != null && existsById(istoric.getId())) { 
                throw new ResourceAlreadyExistsException("Istoric with id: " + istoric.getId() +
                        " already exists");
            }
            return istoric_Repository.save(istoric);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save istoric - Lipsa nume membru");
            exc.addErrorMessage("Istoric is null or empty");
            throw exc;
        }
    }
    
    public void update(Istoric istoric) 
            throws BadResourceException, ResourceNotFoundException {
        if (StringUtils.hasLength(istoric.getNumemembru())) {
            if (!existsById(istoric.getId())) {
                throw new ResourceNotFoundException("Cannot find Contact with id: " + istoric.getId());
            }
            istoric_Repository.save(istoric);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save istoric");
            exc.addErrorMessage("Istoric is null or empty");
            throw exc;
        }
    }
    
    public void deleteById(Long id) throws ResourceNotFoundException  {
        if (!existsById(id)) { 
            throw new ResourceNotFoundException("Cannot find istoric with id: " + id);
        }
        else {
            istoric_Repository.deleteById(id);
        }
    }
    
    public Long count() {
        return  istoric_Repository.count();
    }

    public List<Istoric> findByIdMembru(Long idMembru) throws ResourceNotFoundException {
        List<Istoric> istoric = istoric_Repository.findByIdMembru(idMembru);
        if (istoric==null) {
            throw new ResourceNotFoundException("Cannot find Contact with id: " + idMembru);
        }
        else return istoric;
    }
    
	public Istoric findLastByIdMembru(Long idMembru) throws ResourceNotFoundException {
        Istoric istoric = istoric_Repository.findLastByIdMembru(idMembru);
        if (istoric==null) {
            throw new ResourceNotFoundException("Cannot find Contact with id: " + idMembru);
        }
        else return istoric;
    }
}