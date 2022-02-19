package com.radu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.radu.repository.MembruRepository;
import com.radu.service.MembruService;
import com.radu.exception.ResourceNotFoundException;
import com.radu.model.Membru;

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
public class MembruController {

  private final MembruRepository repository = null;
    
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  
  private final int ROW_PER_PAGE = 5;
  
  @Autowired
  private MembruService membruService;
  
  @GetMapping("/membri")
  public String getMembri(Model model,
		@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		List<Membru> membri = membruService.findAll(pageNumber, ROW_PER_PAGE);
		long count = membruService.count();
		boolean hasPrev = pageNumber > 1;
		boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
		model.addAttribute("membri", membri);
		model.addAttribute("hasPrev", hasPrev);
		model.addAttribute("prev", pageNumber - 1);
		model.addAttribute("hasNext", hasNext);
		model.addAttribute("next", pageNumber + 1);
		return "membri.html";
  }

  @GetMapping(value = "/membri/{id}")
  public String getMembruById(Model model, @PathVariable long id) {
      Membru membru = null;
      try {
        membru = membruService.findById(id);
      } catch (ResourceNotFoundException ex) {
        model.addAttribute("errorMessage", "Membru not found");
      }
      model.addAttribute("membru", membru);
      return "membri.html";
  }
  
  @GetMapping(value = {"/membri/add"})
  public String showAddMembru(Model model) {
      Membru membru = new Membru();
      model.addAttribute("add", true);
      model.addAttribute("membru", membru);

      return "membru-edit.html";
  }

  @PostMapping(value = "/membri/add")
  public String addMembru(Model model,
          @ModelAttribute("membru") Membru membru) {        
      try {
          Membru newMembru = membruService.save(membru);
          return "redirect:/membri";  
      } catch (Exception ex) {
          // log exception first, 
          // then show error
          String errorMessage = ex.getMessage();
          logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
          //model.addAttribute("membru", membru);
          model.addAttribute("add", true);
          return "membru-edit.html";
      }        
  }
  
  //What is @RequestBody in spring boot?
  //Simply put, the @RequestBody annotation maps the HttpRequest body to a transfer or domain object, 
  // enabling automatic deserialization of the inbound HttpRequest body onto a Java object. 
  // Spring automatically deserializes the JSON into a Java type assuming an appropriate one is specified.
		  
  @GetMapping(value = {"/membri/{id}/edit"})
  public String showEditMembru(Model model, @PathVariable long id) {
      Membru membru = null;
      try {
          membru = membruService.findById(id);
      } catch (ResourceNotFoundException ex) {
          model.addAttribute("errorMessage", "Membru not found");
      }
      model.addAttribute("add", false);
      model.addAttribute("membru", membru);
      return "membru-edit.html";
  }

  @PostMapping(value = {"/membri/{id}/edit"})
  public String updateMembru(Model model,
          @PathVariable long id,
          @ModelAttribute("membru") Membru membru) {        
      try {
          membru.setId(id);
          membruService.update(membru);
          return "redirect:/membri";  
      } catch (Exception ex) {
          // log exception first, 
          // then show error
          String errorMessage = ex.getMessage();
          logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
          model.addAttribute("add", false);
          return "membru-edit.html";
      }
  }

  @GetMapping(value = {"/membri/{id}/delete"})
  public String showDeleteMembruById(
          Model model, @PathVariable long id) {
      Membru membru = null;
      try {
          membru = membruService.findById(id);
      } catch (ResourceNotFoundException ex) {
          model.addAttribute("errorMessage", "Membru not found");
      }
      model.addAttribute("allowDelete", true);
      model.addAttribute("membru", membru);
      try {
          membruService.deleteById(id);
          return "redirect:/membri";
      } catch (ResourceNotFoundException ex) {
          String errorMessage = ex.getMessage();
          logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
      }
      return "redirect:/membri";
  }

  /*
  @PostMapping(value = {"/membri/{id}/delete"})
  public String deleteMembruById(
          Model model, @PathVariable long id) {
      try {
          membruService.deleteById(id);
          return "redirect:/membri";
      } catch (ResourceNotFoundException ex) {
          String errorMessage = ex.getMessage();
          logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
          return "membru";
      }
  }
  */
}