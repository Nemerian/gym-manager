package com.radu.service;

import com.radu.exception.BadResourceException;
import com.radu.exception.ResourceNotFoundException;
import com.radu.model.Istoric_Abonati;
import com.radu.repository.Istoric_Abonati_Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
 
@Service
public class Istoric_Abonati_Service {

   
    @Autowired
    private Istoric_Abonati_Repository istoric_abonati_Repository;
    
    private boolean existsById(Long id) {
        return istoric_abonati_Repository.existsById(id);
    }
    
    /*public Istoric_Abonati findById(Long id) throws ResourceNotFoundException {
        Istoric_Abonati istoric_abonati= Istoric_Abonati_Repository.findById(id).orElse(null);
        if (istoric_abonati==null) {
            throw new ResourceNotFoundException("Cannot find Contact with id: " + id);
        }
        else return istoric_abonati;
    }
    
    public List<Istoric_Abonati> findAll(int pageNumber, int rowPerPage) {
        List<Istoric_Abonati> istoric_abonati = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage, 
                Sort.by("id").ascending());
                Istoric_Abonati_Repository.findAll(sortedByIdAsc).forEach(istoric_abonati::add);
        return istoric_abonati;
    }
    
    public Istoric_Abonati save(Istoric_Abonati istoric_abonati) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.isEmpty(istoric_abonati.getNumeMembru())) {
            if (istoric_abonati.getId() != null && existsById(istoric_abonati.getId())) { 
                throw new ResourceAlreadyExistsException("Istoric Abonati with id: " + Istoric_Abonati.getId() +
                        " already exists");
            }
            return istoric_abonati_Repository.save(istoric_abonati);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save istoric abonati");
            exc.addErrorMessage("Istoric abonati is null or empty");
            throw exc;
        }
    }*/
    
    public void update(Istoric_Abonati istoric_abonati) 
            throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(istoric_abonati.getNumeMembru())) {
            if (!existsById(istoric_abonati.getId())) {
                throw new ResourceNotFoundException("Cannot find Contact with id: " + istoric_abonati.getId());
            }
            istoric_abonati_Repository.save(istoric_abonati);
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
            istoric_abonati_Repository.deleteById(id);
        }
    }
    
    public Long count() {
        return  istoric_abonati_Repository.count();
    }
}