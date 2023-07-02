package com.radu.service;

import com.radu.repository.HistoryRepository;
import com.radu.exception.BadResourceException;
import com.radu.exception.ResourceAlreadyExistsException;
import com.radu.exception.ResourceNotFoundException;
import com.radu.model.History;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HistoryService {

   
    @Autowired
    private HistoryRepository historyRepository;
    
    private boolean existsById(Long id) {
        return historyRepository.existsById(id);
    }
    
    public History findById(Long id) throws ResourceNotFoundException {
        History history = historyRepository.findById(id).orElse(null);
        if (history==null) {
            throw new ResourceNotFoundException("Cannot find Contact with id: " + id);
        }
        else return history;
    }

	public List<History> findAll(int pageNumber, int rowPerPage) {
        List<History> history = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage, 
                Sort.by("id").ascending());
                historyRepository.findAll(sortedByIdAsc).forEach(history::add);
        return history;
    }
    
    public History save(History history) throws BadResourceException, ResourceAlreadyExistsException {
        if (StringUtils.hasLength(history.getMemberFamilyName())) {
            if (history.getId() != null && existsById(history.getId())) { 
                throw new ResourceAlreadyExistsException("history with id: " + history.getId() +
                        " already exists");
            }
            return historyRepository.save(history);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save history - Lipsa nume member");
            exc.addErrorMessage("history is null or empty");
            throw exc;
        }
    }
    
    public void update(History history) 
            throws BadResourceException, ResourceNotFoundException {
        if (StringUtils.hasLength(history.getMemberFamilyName())) {
            if (!existsById(history.getId())) {
                throw new ResourceNotFoundException("Cannot find Contact with id: " + history.getId());
            }
            historyRepository.save(history);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save history");
            exc.addErrorMessage("history is null or empty");
            throw exc;
        }
    }
    
    public void deleteById(Long id) throws ResourceNotFoundException  {
        if (!existsById(id)) { 
            throw new ResourceNotFoundException("Cannot find history with id: " + id);
        }
        else {
            historyRepository.deleteById(id);
        }
    }
    
    public Long count() {
        return  historyRepository.count();
    }

    public List<History> findByIdmember(Long idmember) throws ResourceNotFoundException {
        List<History> history = historyRepository.findByIdmember(idmember);
        if (history==null) {
            throw new ResourceNotFoundException("Cannot find Contact with id: " + idmember);
        }
        else return history;
    }
    
	public History findLastByIdmember(Long idmember) throws ResourceNotFoundException {
        History history = historyRepository.findFirstByidmemberOrderByDatainceputDesc(idmember);
        if (history==null) {
            throw new ResourceNotFoundException("Cannot find Contact with id: " + idmember);
        }
        else return history;
    }
}