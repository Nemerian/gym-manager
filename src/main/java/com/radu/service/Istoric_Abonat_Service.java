package com.radu.service;

import com.radu.repository.Istoric_Abonat_Repository;
import com.radu.exception.BadResourceException;
import com.radu.exception.ResourceAlreadyExistsException;
import com.radu.exception.ResourceNotFoundException;
import com.radu.model.Istoric_Abonat;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class Istoric_Abonat_Service {

   
    @Autowired
    private Istoric_Abonat_Repository istoric_Abonat_Repository;
    
    private boolean existsById(Long id) {
        return istoric_Abonat_Repository.existsById(id);
    }
    
    public Istoric_Abonat findById(Long id) throws ResourceNotFoundException {
        Istoric_Abonat istoric_abonat = istoric_Abonat_Repository.findById(id).orElse(null);
        if (istoric_abonat==null) {
            throw new ResourceNotFoundException("Cannot find Contact with id: " + id);
        }
        else return istoric_abonat;
    }
    
	public List<Istoric_Abonat> findByIdMembru(Long idMembru) throws ResourceNotFoundException {
        List<Istoric_Abonat> istoric_abonat = istoric_Abonat_Repository.findByIdMembru(idMembru);
        if (istoric_abonat==null) {
            throw new ResourceNotFoundException("Cannot find Contact with id: " + idMembru);
        }
        else return istoric_abonat;
    }
    
	public Istoric_Abonat findLastByIdMembru(Long idMembru) throws ResourceNotFoundException {
        Istoric_Abonat istoric_abonat = istoric_Abonat_Repository.findLastByIdMembru(idMembru);
        if (istoric_abonat==null) {
            throw new ResourceNotFoundException("Cannot find Contact with id: " + idMembru);
        }
        else return istoric_abonat;
    }

	public List<Istoric_Abonat> findAll(int pageNumber, int rowPerPage) {
        List<Istoric_Abonat> istoric_abonati = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage, 
                Sort.by("id").ascending());
                istoric_Abonat_Repository.findAll(sortedByIdAsc).forEach(istoric_abonati::add);
        return istoric_abonati;
    }
    
    public Istoric_Abonat save(Istoric_Abonat istoric_abonat) throws BadResourceException, ResourceAlreadyExistsException {
        if (StringUtils.hasLength(istoric_abonat.getNumeMembru())) {
            if (istoric_abonat.getId() != null && existsById(istoric_abonat.getId())) { 
                throw new ResourceAlreadyExistsException("Istoric Abonati with id: " + istoric_abonat.getId() +
                        " already exists");
            }
            return istoric_Abonat_Repository.save(istoric_abonat);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save istoric abonati");
            exc.addErrorMessage("Istoric abonati is null or empty");
            throw exc;
        }
    }
    
    public void update(Istoric_Abonat istoric_abonat) 
            throws BadResourceException, ResourceNotFoundException {
        if (StringUtils.hasLength(istoric_abonat.getNumeMembru())) {
            if (!existsById(istoric_abonat.getId())) {
                throw new ResourceNotFoundException("Cannot find Contact with id: " + istoric_abonat.getId());
            }
            istoric_Abonat_Repository.save(istoric_abonat);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save istoric abonati");
            exc.addErrorMessage("Istoric Abonati is null or empty");
            throw exc;
        }
    }
    
    public void deleteById(Long id) throws ResourceNotFoundException  {
        if (!existsById(id)) { 
            throw new ResourceNotFoundException("Cannot find istoric abonati with id: " + id);
        }
        else {
            istoric_Abonat_Repository.deleteById(id);
        }
    }
    
    public Long count() {
        return  istoric_Abonat_Repository.count();
    }
}