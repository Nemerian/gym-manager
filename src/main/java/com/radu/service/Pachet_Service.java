package com.radu.service;
 
import com.radu.repository.Pachet_Repository;
import com.radu.exception.BadResourceException;
import com.radu.exception.ResourceAlreadyExistsException;
import com.radu.exception.ResourceNotFoundException;
import com.radu.model.Pachet;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
 
@Service
public class Pachet_Service {

   
    @Autowired
    private Pachet_Repository Pachet_Repository;
    
    private boolean existsById(Long id) {
        return Pachet_Repository.existsById(id);
    }
    
    public Pachet findById(Long id) throws ResourceNotFoundException {
        Pachet Pachet = Pachet_Repository.findById(id).orElse(null);
        if (Pachet==null) {
            throw new ResourceNotFoundException("Cannot find Contact with id: " + id);
        }
        else return Pachet;
    }
    
    public List<Pachet> findAll(int pageNumber, int rowPerPage) {
        List<Pachet> Pachet = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage, 
                Sort.by("id").ascending());
        Pachet_Repository.findAll(sortedByIdAsc).forEach(Pachet::add);
        return Pachet;
    }
    
    public List<Pachet> findAll() {
        List<Pachet> Pachet = new ArrayList<>();
        Pachet_Repository.findAll().forEach(Pachet::add);
        return Pachet;
    }

    public Pachet save(Pachet Pachet) throws BadResourceException, ResourceAlreadyExistsException {
        if (StringUtils.hasLength(Pachet.getDenumire())) {
            if (Pachet.getId() != null && existsById(Pachet.getId())) { 
                throw new ResourceAlreadyExistsException("Pachet with id: " + Pachet.getId() +
                        " already exists");
            }
            return  Pachet_Repository.save(Pachet);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save Pachet");
            exc.addErrorMessage("Pachet is null or empty");
            throw exc;
        }
    }
    
    public void update(Pachet Pachet) 
            throws BadResourceException, ResourceNotFoundException {
        if (StringUtils.hasLength(Pachet.getDenumire())) {
            if (!existsById(Pachet.getId())) {
                throw new ResourceNotFoundException("Cannot find Contact with id: " + Pachet.getId());
            }
             Pachet_Repository.save(Pachet);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save Pachet");
            exc.addErrorMessage("Pachet is null or empty");
            throw exc;
        }
    }
    
    public void deleteById(Long id) throws ResourceNotFoundException  {
        if (!existsById(id)) { 
            throw new ResourceNotFoundException("Cannot find Pachet with id: " + id);
        }
        else {
            Pachet_Repository.deleteById(id);
        }
    }
    
    public Long count() {
        return Pachet_Repository.count();
    }
}