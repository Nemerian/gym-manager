package com.radu.service;
 
import com.radu.repository.Membru_Repository;
import com.radu.exception.BadResourceException;
import com.radu.exception.ResourceAlreadyExistsException;
import com.radu.exception.ResourceNotFoundException;
import com.radu.model.Membru;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
 
@Service
public class Membru_Service {

   
    @Autowired
    private Membru_Repository membru_Repository;
    
    private boolean existsById(Long id) {
        return membru_Repository.existsById(id);
    }
    
    public Membru findById(Long id) throws ResourceNotFoundException {
        Membru membru = membru_Repository.findById(id).orElse(null);
        if (membru==null) {
            throw new ResourceNotFoundException("Cannot find Contact with id: " + id);
        }
        else return membru;
    }
    
    public List<Membru> findAll(int pageNumber, int rowPerPage) {
        List<Membru> membri = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage, 
                Sort.by("id").ascending());
        membru_Repository.findAll(sortedByIdAsc).forEach(membri::add);
        return  membri;
    }
    
    public Membru save(Membru membru) throws BadResourceException, ResourceAlreadyExistsException {
        if (StringUtils.hasLength(membru.getNume())) {
            if (membru.getId() != null && existsById(membru.getId())) { 
                throw new ResourceAlreadyExistsException("Membru with id: " +  membru.getId() +
                        " already exists");
            }
            return membru_Repository.save(membru);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save membru");
            exc.addErrorMessage("Membru is null or empty");
            throw exc;
        }
    }
    
    public void update(Membru membru) 
            throws BadResourceException, ResourceNotFoundException {
        if (StringUtils.hasLength(membru.getNume())) {
            if (!existsById(membru.getId())) {
                throw new ResourceNotFoundException("Cannot find Membru with id: "+ membru.getId());
            }
            membru_Repository.save(membru);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save membru");
            exc.addErrorMessage("Membru is null or empty");
            throw exc;
        }
    }
    
    public void deleteById(Long id) throws ResourceNotFoundException  {
        if (!existsById(id)) { 
            throw new ResourceNotFoundException("Cannot find membru with id: " + id);
        }
        else {
            membru_Repository.deleteById(id);
        }
    }
    
    public Long count() {
        return membru_Repository.count();
    }
}