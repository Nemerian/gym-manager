package com.radu.service;
 
import com.radu.repository.SubscriptionRepository;
import com.radu.exception.BadResourceException;
import com.radu.exception.ResourceAlreadyExistsException;
import com.radu.exception.ResourceNotFoundException;
import com.radu.model.Subscription;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
 
@Service
public class SubscriptionService {

   
    @Autowired
    private SubscriptionRepository packageRepository;
    
    private boolean existsById(Long id) {
        return packageRepository.existsById(id);
    }
    
    public Subscription findById(Long id) throws ResourceNotFoundException {
        Subscription subscription = packageRepository.findById(id).orElse(null);
        if (subscription==null) {
            throw new ResourceNotFoundException("Cannot find Contact with id: " + id);
        }
        else return subscription;
    }
    
    public List<Subscription> findAll(int pageNumber, int rowPerPage) {
        List<Subscription> subscriptionList = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage, 
                Sort.by("id").ascending());
                packageRepository.findAll(sortedByIdAsc).forEach(subscriptionList::add);
        return subscriptionList;
    }
    
    public List<Subscription> findAll() {
        List<Subscription> subscriptionList = new ArrayList<>();
        packageRepository.findAll().forEach(subscriptionList::add);
        return subscriptionList;
    }

    public Subscription save(Subscription subscription) throws BadResourceException, ResourceAlreadyExistsException {
        if (StringUtils.hasLength(subscription.getSubscriptionName())) {
            if (subscription.getId() != null && existsById(subscription.getId())) { 
                throw new ResourceAlreadyExistsException("package with id: " + subscription.getId() +
                        " already exists");
            }
            return packageRepository.save(subscription);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save package");
            exc.addErrorMessage("package is null or empty");
            throw exc;
        }
    }
    
    public void update(Subscription subscription) 
            throws BadResourceException, ResourceNotFoundException {
        if (StringUtils.hasLength(subscription.getSubscriptionName())) {
            if (!existsById(subscription.getId())) {
                throw new ResourceNotFoundException("Cannot find Contact with id: " + subscription.getId());
            }
            packageRepository.save(subscription);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save package");
            exc.addErrorMessage("package is null or empty");
            throw exc;
        }
    }
    
    public void deleteById(Long id) throws ResourceNotFoundException  {
        if (!existsById(id)) { 
            throw new ResourceNotFoundException("Cannot find package with id: " + id);
        }
        else {
            packageRepository.deleteById(id);
        }
    }
    
    public Long count() {
        return packageRepository.count();
    }
}