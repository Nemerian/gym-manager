package com.radu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.radu.repository.Pachet_Repository;
import com.radu.service.Pachet_Service;
import com.radu.exception.ResourceNotFoundException;
import com.radu.model.Membru;
import com.radu.model.Pachet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@RestController
@Controller
public class Pachet_Controller {

  private final Pachet_Repository repository = null;
  
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final int ROW_PER_PAGE = 5;

  @Autowired
  private Pachet_Service pachet_Service;
 
  @GetMapping("/pachete")
  public String getPachete(Model model,
      @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
  	  List<Pachet> pachete = pachet_Service.findAll(pageNumber, ROW_PER_PAGE);
      long count = pachet_Service.count();
      boolean hasPrev = pageNumber > 1;
      boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
      model.addAttribute("pachete", pachete);
      model.addAttribute("hasPrev", hasPrev);
      model.addAttribute("prev", pageNumber - 1);
      model.addAttribute("hasNext", hasNext);
      model.addAttribute("next", pageNumber + 1);
      return "pachete.html";
  }
  
@GetMapping(value = "/pachete/{id}")
public String getPachetById(Model model, @PathVariable long id) {
    Pachet pachet = null;
    try {
      pachet = pachet_Service.findById(id);
    } catch (ResourceNotFoundException ex) {
      model.addAttribute("errorMessage", "Pachet not found");
    }
    model.addAttribute("pachet", pachet);
    return "pachete.html";
}

@GetMapping(value = {"/pachete/add"})
public String showAddPachet(Model model) {
    Pachet pachet = new Pachet();
    model.addAttribute("add", true);
    model.addAttribute("pachet", pachet);
    return "pachet-edit.html";
}

@PostMapping(value = "/pachete/add")
public String addPachete(Model model, @ModelAttribute("pachet") Pachet pachet) {        
    try {
        Pachet newPachet = pachet_Service.save(pachet);
        return "redirect:/pachete";  
    } catch (Exception ex) {
        // log exception first, 
        // then show error
        String errorMessage = ex.getMessage();
        logger.error(errorMessage);
        model.addAttribute("errorMessage", errorMessage);
        //model.addAttribute("membru", membru);
        model.addAttribute("add", true);
        return "pachet-edit.html";
    }        
}
  /*
  @PutMapping("/pachete/{id}")
  Pachet replacePachet(@RequestBody Pachet newPachet, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(Pachet -> {
    	  Pachet.setId(newPachet.getId());
    	  Pachet.setDenumire(newPachet.getDenumire());
        return repository.save(Pachet);
      })
      .orElseGet(() -> {
    	  newPachet.setId(id);
        return repository.save(newPachet);
      });
  }*/

  @GetMapping(value = {"/pachete/{id}/edit"})
  public String showEditPachet(Model model, @PathVariable long id) {
      Pachet pachet = null;
      try {
          pachet = pachet_Service.findById(id);
      } catch (ResourceNotFoundException ex) {
          model.addAttribute("errorMessage", "Pachet not found");
      }
      model.addAttribute("add", false);
      model.addAttribute("pachet", pachet);
      return "pachet-edit.html";
    }
  
  @PostMapping(value = {"/pachete/{id}/edit"})
  public String updateContact(Model model,
          @PathVariable long id,
          @ModelAttribute("Pachet") Pachet pachet) {        
      try {
        pachet.setId(id);
        pachet_Service.update(pachet);
          return "redirect:/pachete";
      } catch (Exception ex) {
          // log exception first, 
          // then show error
          String errorMessage = ex.getMessage();
     //     logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
          model.addAttribute("add", false);
          return "pachet-edit";
      }
  }
  
  @GetMapping(value = {"/pachete/{id}/delete"})
  public String showDeletePachetById(
          Model model, @PathVariable long id) {
      Pachet pachet = null;
      try {
        pachet = pachet_Service.findById(id);
      } catch (ResourceNotFoundException ex) {
          model.addAttribute("errorMessage", "Pachet not found");
      }
      model.addAttribute("allowDelete", true);
      model.addAttribute("pachet", pachet);
      try {
          pachet_Service.deleteById(id);
          return "redirect:/pachete";
      } catch (ResourceNotFoundException ex) {
          String errorMessage = ex.getMessage();
          logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
      }
      return "redirect:/pachete";
  }
}