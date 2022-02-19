package com.radu.controller;

import com.radu.model.Istoric_Abonati;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

	@Component
	class Istoric_Abonati_ModelAssembler implements RepresentationModelAssembler<Istoric_Abonati, EntityModel<Istoric_Abonati>> {

	  @Override
	  public EntityModel<Istoric_Abonati> toModel(Istoric_Abonati istoric_Abonati) {

	    return EntityModel.of(istoric_Abonati, //
	        linkTo(methodOn(Istoric_Abonati_Controller.class).one(istoric_Abonati.getId())).withSelfRel(),
	        linkTo(methodOn(Istoric_Abonati_Controller.class).all()).withRel("Istoric Abonati"));
	  }
	}
