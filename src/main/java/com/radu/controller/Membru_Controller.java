package com.radu.controller;

import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.radu.repository.Membru_Repository;
import com.radu.service.Istoric_Service;
import com.radu.service.Membru_Service;
import com.radu.service.Pachet_Service;
import com.radu.exception.ResourceNotFoundException;
import com.radu.model.Istoric;
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
public class Membru_Controller {

  //private final Membru_Repository repository = null;
    
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  
  private final int ROW_PER_PAGE = 5;
  
  @Autowired
  private Membru_Service membru_Service;
  
  @Autowired
  private Istoric_Service istoric_Service;

  @Autowired
  private Pachet_Service pachet_Service;
    
  @GetMapping("/membri")
  public String getMembri(Model model,
		@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		List<Membru> membri = membru_Service.findAll(pageNumber, ROW_PER_PAGE);
		long count = membru_Service.count();
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
        membru = membru_Service.findById(id);
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
  public String addMembru(Model model, @ModelAttribute("membru") Membru membru) {        
      try {
          membru_Service.save(membru);
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
 
  
  @GetMapping(value = {"/membri/{id}/edit"})
  public String showEditMembru(Model model, @PathVariable long id) {
      Membru membru = null;
      try {
          membru = membru_Service.findById(id);
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
          membru_Service.update(membru);
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
          membru = membru_Service.findById(id);
      } catch (ResourceNotFoundException ex) {
          model.addAttribute("errorMessage", "Membru not found");
      }
      model.addAttribute("allowDelete", true);
      model.addAttribute("membru", membru);
      try {
          membru_Service.deleteById(id);
          return "redirect:/membri";
      } catch (ResourceNotFoundException ex) {
          String errorMessage = ex.getMessage();
          logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
      }
      return "redirect:/membri";
  }
  
  @GetMapping(value = {"/membri/{idmembru}/istoric/edit"})
  public String showEditMembruIstoric(Model model, @PathVariable long idmembru) {
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
         // model.addAttribute("errorMessage", "Istoric not found");
      }
      if(istoric==null) {
    	istoric=new Istoric();  
        model.addAttribute("add", true);
      }
      else {
    	  Calendar obj = Calendar.getInstance();
          Date datacrt = (Date) obj.getTime();
    	  if(istoric.getDatasfarsit().after(datacrt)) {
  	        model.addAttribute("add", false);
    	  }
      }
      istoric.setIdmembru(idmembru);
      istoric.setNumemembru(membru.getNume()+" "+membru.getPrenume());
      istoric.setIdtippachet(1L);
      model.addAttribute("membru", membru);
      model.addAttribute("istoric", istoric);
      model.addAttribute("pachete", pachete);
      return "membru-istoric-edit.html";
  }
  
  @PostMapping(value = {"/membri/{idmembru}/istoric/edit"})
  public String updateMembru(Model model, @PathVariable long idmembru, 
		  @ModelAttribute("istoric") Istoric istoric) {        
      try {
    	  istoric_Service.update(istoric);
          return "redirect:/membri";  
      } catch (Exception ex) {
          // log exception first, 
          // then show error
          String errorMessage = ex.getMessage();
          logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
          model.addAttribute("add", false);
          return "membru-istoric-edit.html";
      }
  }
  
  @PostMapping(value = "/membri/istoric/add")
  public String addMembruIstoric(Model model, @ModelAttribute("istoric") Istoric istoric) {        
      try {
          istoric_Service.save(istoric);
          return "redirect:/membri";  
      } catch (Exception ex) {
          // log exception first, 
          // then show error
          String errorMessage = ex.getMessage();
          logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
          model.addAttribute("add", true);
          return "membru-istoric-edit.html";
      }        
  }
  
}