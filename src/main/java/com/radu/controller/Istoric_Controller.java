package com.radu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.radu.repository.Istoric_Repository;
import com.radu.service.Istoric_Service;
import com.radu.exception.ResourceNotFoundException;
import com.radu.model.Istoric;
import com.radu.model.Membru;
import com.radu.model.Pachet;
import com.radu.service.Membru_Service;
import com.radu.service.Pachet_Service;
import com.radu.service.Istoric_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Istoric_Controller {

  private final Istoric_Repository repository = null;
  
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final int ROW_PER_PAGE = 5;

  @Autowired
  private Membru_Service membru_Service;

  @Autowired
  private Istoric_Service istoric_Service;

  @Autowired
  private Pachet_Service pachet_Service;
  
  @GetMapping("/istoric")
  public String getistoric(Model model,
		@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		List<Istoric> istoric = istoric_Service.findAll(pageNumber, ROW_PER_PAGE);
		long count = istoric_Service.count();
		boolean hasPrev = pageNumber > 1;
		boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
		model.addAttribute("istoric", istoric);
		model.addAttribute("hasPrev", hasPrev);
		model.addAttribute("prev", pageNumber - 1);
		model.addAttribute("hasNext", hasNext);
		model.addAttribute("next", pageNumber + 1);
		return "istoric.html";
  }

  @GetMapping(value = "/istoric/{id}")
  public String getistoricById(Model model, @PathVariable long id) {
      Istoric istoric = null;
      try {
        istoric = istoric_Service.findById(id);
      } catch (ResourceNotFoundException ex) {
        model.addAttribute("errorMessage", "Istoric not found");
      }
      model.addAttribute("istoric", istoric);
      return "istoric.html";
  }
  
/*
  @GetMapping(value = {"/istoric/add"})
  public String showAddIstoric(Model model) {
      Istoric istoric = new Istoric();
      model.addAttribute("add", true);
      model.addAttribute("Istoric", istoric);

      return "istoric-edit.html";
  }

  @PostMapping(value = "/istoric/add")
  public String addIstoric(Model model,
      @ModelAttribute("istoric") Istoric istoric) {        
      try {
          Istoric newistoric = istoric_Service.save(istoric);
          return "redirect:/istoric";  
      } catch (Exception ex) {
          // log exception first, 
          // then show error
          String errorMessage = ex.getMessage();
          logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
          model.addAttribute("add", true);
          return "istoric-edit.html";
      }        
  }
*/

  //What is @RequestBody in spring boot?
  //Simply put, the @RequestBody annotation maps the HttpRequest body to a transfer or domain object, 
  // enabling automatic deserialization of the inbound HttpRequest body onto a Java object. 
  // Spring automatically deserializes the JSON into a Java type assuming an appropriate one is specified.
	

  @GetMapping(value = {"/istoric/{idmembru}/edit"})
  public String showEditIstoric(Model model, @PathVariable long idmembru) {
      Membru membru = null;
      try {
          membru = membru_Service.findById(idmembru);
      } catch (ResourceNotFoundException ex) {
          model.addAttribute("errorMessage", "Membru not found");
      }
  	  List<Pachet> pachete = pachet_Service.findAll();
      Istoric istoric = null;
      try {
         istoric = istoric_Service.findLastByIdMembru(idmembru);
      } catch (ResourceNotFoundException ex) {
          model.addAttribute("errorMessage", "Istoric not found");
      }
      model.addAttribute("add", false);
      model.addAttribute("membru", membru);
      model.addAttribute("pachete", pachete);
      model.addAttribute("istoric", istoric);
      return "istoric-edit.html";
  }

 /*
    @PostMapping(value = {"/istoric/{id}/edit"})
  public String updateistoric(Model model,
          @PathVariable long id,
          @ModelAttribute("istoric") Istoric istoric) {        
      try {
          istoric.setIdMembru(id);
          istoric_Service.update(istoric);
          return "redirect:/istoric";  
      } catch (Exception ex) {
          // log exception first, 
          // then show error
          String errorMessage = ex.getMessage();
          logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
          model.addAttribute("add", false);
          return "istoric-edit.html";
      }
  }
*/
  
  @GetMapping(value = {"/istoric/{id}/delete"})
  public String showDeleteistoricById(
        Model model, @PathVariable long id) {
        Istoric istoric = null;
      try {
        istoric = istoric_Service.findById(id);
      } catch (ResourceNotFoundException ex) {
          model.addAttribute("errorMessage", "Istoric not found");
      }
      model.addAttribute("allowDelete", true);
      model.addAttribute("istoric", istoric);
      try {
          istoric_Service.deleteById(id);
          return "redirect:/istoric";
      } catch (ResourceNotFoundException ex) {
          String errorMessage = ex.getMessage();
          logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
      }
      return "redirect:/istoric";
  }
}