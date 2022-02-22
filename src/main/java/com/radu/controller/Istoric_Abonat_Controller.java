package com.radu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.radu.repository.Istoric_Abonat_Repository;
import com.radu.service.Istoric_Abonat_Service;
import com.radu.exception.ResourceNotFoundException;
import com.radu.model.Istoric_Abonat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Istoric_Abonat_Controller {

  private final Istoric_Abonat_Repository repository = null;
  
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final int ROW_PER_PAGE = 5;

  @Autowired
  private Istoric_Abonat_Service istoric_Abonat_Service;
  
  @GetMapping("/istoric_abonati")
  public String getistoric(Model model,
		@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		List<Istoric_Abonat> istoric_Abonati = istoric_Abonat_Service.findAll(pageNumber, ROW_PER_PAGE);
		long count = istoric_Abonat_Service.count();
		boolean hasPrev = pageNumber > 1;
		boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
		model.addAttribute("istoric_abonati", istoric_Abonati);
		model.addAttribute("hasPrev", hasPrev);
		model.addAttribute("prev", pageNumber - 1);
		model.addAttribute("hasNext", hasNext);
		model.addAttribute("next", pageNumber + 1);
		return "istoric_abonati.html";
  }

  @GetMapping(value = "/istoric_abonati/{id}")
  public String getistoricById(Model model, @PathVariable long id) {
      Istoric_Abonat istoric_abonat = null;
      try {
        istoric_abonat = istoric_Abonat_Service.findById(id);
      } catch (ResourceNotFoundException ex) {
        model.addAttribute("errorMessage", "Istoric not found");
      }
      model.addAttribute("istoric_abonat", istoric_abonat);
      return "istoric_abonati.html";
  }
  
  @GetMapping(value = {"/istoric_abonati/add"})
  public String showAddIstoric(Model model) {
      Istoric_Abonat istoric_Abonat = new Istoric_Abonat();
      model.addAttribute("add", true);
      model.addAttribute("Istoric_Abonat", istoric_Abonat);

      return "istoric_abonati-edit.html";
  }

  @PostMapping(value = "/istoric_abonati/add")
  public String addIstoric(Model model,
          @ModelAttribute("istoric_abonat") Istoric_Abonat istoric_abonat) {        
      try {
          Istoric_Abonat newistoric = istoric_Abonat_Service.save(istoric_abonat);
          return "redirect:/istoric_abonati";  
      } catch (Exception ex) {
          // log exception first, 
          // then show error
          String errorMessage = ex.getMessage();
          logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
          model.addAttribute("add", true);
          return "istoric_abonati-edit.html";
      }        
  }
  
  //What is @RequestBody in spring boot?
  //Simply put, the @RequestBody annotation maps the HttpRequest body to a transfer or domain object, 
  // enabling automatic deserialization of the inbound HttpRequest body onto a Java object. 
  // Spring automatically deserializes the JSON into a Java type assuming an appropriate one is specified.
		  
  @GetMapping(value = {"/istoric_abonati/{id}/edit"})
  public String showEditIstoric(Model model, @PathVariable long id) {
      Istoric_Abonat istoric_abonat = null;
      try {
        istoric_abonat = istoric_Abonat_Service.findById(id);
      } catch (ResourceNotFoundException ex) {
          model.addAttribute("errorMessage", "Istoric abonat not found");
      }
      model.addAttribute("add", false);
      model.addAttribute("istoric_abonat", istoric_abonat);
      return "istoric_abonati-edit.html";
  }

  @PostMapping(value = {"/istoric_abonati/{id}/edit"})
  public String updateistoric_abonat(Model model,
          @PathVariable long id,
          @ModelAttribute("istoric_abonat") Istoric_Abonat istoric_abonat) {        
      try {
          istoric_abonat.setId(id);
          istoric_Abonat_Service.update(istoric_abonat);
          return "redirect:/istoric_abonati";  
      } catch (Exception ex) {
          // log exception first, 
          // then show error
          String errorMessage = ex.getMessage();
          logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
          model.addAttribute("add", false);
          return "istoric_abonat-edit.html";
      }
  }

  @GetMapping(value = {"/istoric_abonati/{id}/delete"})
  public String showDeleteistoricabonatById(
        Model model, @PathVariable long id) {
        Istoric_Abonat istoric_abonat = null;
      try {
        istoric_abonat = istoric_Abonat_Service.findById(id);
      } catch (ResourceNotFoundException ex) {
          model.addAttribute("errorMessage", "Istoric abonat not found");
      }
      model.addAttribute("allowDelete", true);
      model.addAttribute("istoric_abonat", istoric_abonat);
      try {
          istoric_Abonat_Service.deleteById(id);
          return "redirect:/istoric_abonati";
      } catch (ResourceNotFoundException ex) {
          String errorMessage = ex.getMessage();
          logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
      }
      return "redirect:/istoric_abonati";
  }
}