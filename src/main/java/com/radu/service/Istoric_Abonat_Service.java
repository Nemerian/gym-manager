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
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class Istoric_Abonat_Service {

   
    @Autowired
    private Istoric_Abonat_Repository istoric_abonat_Repository;
    
    private boolean existsById(Long id) {
        return istoric_abonat_Repository.existsById(id);
    }
    
    public Istoric_Abonat findById(Long id) throws ResourceNotFoundException {
        Istoric_Abonat istoric_abonat = Istoric_Abonat_Repository.findById(id).orElse(null);
        if (istoric_abonat==null) {
            throw new ResourceNotFoundException("Cannot find Contact with id: " + id);
        }
        else return istoric_abonat;
    }
    
    public List<Istoric_Abonat> findAll(int pageNumber, int rowPerPage) {
        List<Istoric_Abonat> istoric_abonati = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage, 
                Sort.by("id").ascending());
                Istoric_Abonat_Repository.findAll(sortedByIdAsc).forEach(istoric_abonati::add);
        return istoric_abonati;
    }
    
    public Istoric_Abonat save(Istoric_Abonat istoric_abonati) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.isEmpty(istoric_abonati.getNumeMembru())) {
            if (istoric_abonati.getId() != null && existsById(istoric_abonati.getId())) { 
                throw new ResourceAlreadyExistsException("Istoric Abonati with id: " + Istoric_Abonat.getId() +
                        " already exists");
            }
            return istoric_abonat_Repository.save(istoric_abonati);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save istoric abonati");
            exc.addErrorMessage("Istoric abonati is null or empty");
            throw exc;
        }
    }
    
    public void update(Istoric_Abonat istoric_abonat) 
            throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(istoric_abonat.getNumeMembru())) {
            if (!existsById(istoric_abonat.getId())) {
                throw new ResourceNotFoundException("Cannot find Contact with id: " + istoric_abonat.getId());
            }
            istoric_abonat_Repository.save(istoric_abonat);
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
            istoric_abonat_Repository.deleteById(id);
        }
    }
    
    public Long count() {
        return  istoric_abonat_Repository.count();
    }
}