package com.radu.controller;

import java.util.List;

import com.radu.repository.Istoric_Abonati_Repository;
import com.radu.service.Istoric_Abonati_Service;
import com.radu.model.Istoric_Abonati;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Istoric_Abonati_Controller {

  private final Istoric_Abonati_Repository repository;
  
  private final Istoric_Abonati_ModelAssembler assembler;
  
  private final int ROW_PER_PAGE = 5;
  @Autowired
  private Istoric_Abonati_Service Istoric_Abonati_Service;

  Istoric_Abonati_Controller(Istoric_Abonati_Repository repository, Istoric_Abonati_ModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }
/**  
  @GetMapping("/istoric")
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
  
  // end::get-aggregate-root[]
*/
  /*
  @GetMapping("/istoric")
  CollectionModel<EntityModel<Membru>> all() {

    List<EntityModel<Membru>> membri = repository.findAll().stream()
        .map(membri -> EntityModel.of(membri,
            linkTo(methodOn(MembruController.class).one(membri.getId())).withSelfRel(),
            linkTo(methodOn(MembruController.class).all()).withRel("membri")))
        .collect(Collectors.toList());

    return CollectionModel.of(membri, linkTo(methodOn(MembruController.class).all()).withSelfRel());
  }
  */
   
  // Single item
  
  @GetMapping("/istoric_abonati/{id}")
  EntityModel<Istoric_Abonati> one(@PathVariable Long id) {

    Istoric_Abonati istoric_abonati = repository.getById(id);
     
    return assembler.toModel(istoric_abonati);
  }
 
  List<Istoric_Abonati> all() {
	    return repository.findAll();
  }
  
  @PutMapping("/istoric_abonati/{id}")
  Istoric_Abonati replace_istoric(@RequestBody Istoric_Abonati new_Istoric, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(istoric_abonati -> {
    	  istoric_abonati.setNumeMembru(new_Istoric.getNumeMembru());
    	  istoric_abonati.setDenumirePachet(new_Istoric.getDenumirePachet());
        return repository.save(istoric_abonati);
      })
      .orElseGet(() -> {
    	  new_Istoric.setId(id);
        return repository.save(new_Istoric);
      });
  }

  @DeleteMapping("/istoric_abonati/{id}")
  void deleteMembru(@PathVariable Long id) {
    repository.deleteById(id);
  }
}