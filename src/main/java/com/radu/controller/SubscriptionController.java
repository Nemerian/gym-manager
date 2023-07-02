package com.radu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.radu.service.MemberService;
import com.radu.service.SubscriptionService;
import com.radu.exception.ResourceNotFoundException;
import com.radu.model.Member;
import com.radu.model.Subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubscriptionController {
    
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  
  private final int ROW_PER_PAGE = 5;
  
  @Autowired
  private MemberService memberService;

  @Autowired
  private SubscriptionService subscriptionService;
    
  @GetMapping("/packages")
  public String getSubscriptions(Model model,
		@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		List<Subscription> packageList = subscriptionService.findAll(pageNumber, ROW_PER_PAGE);
		long count = memberService.count();
		boolean hasPrev = pageNumber > 1;
		boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
		model.addAttribute("package", packageList);
		model.addAttribute("hasPrev", hasPrev);
		model.addAttribute("prev", pageNumber - 1);
		model.addAttribute("hasNext", hasNext);
		model.addAttribute("next", pageNumber + 1);
		return "subscriptions.html";
  }

  @GetMapping(value = "/packages/{id}")
  public String getpackageById(Model model, @PathVariable long id) {
      Subscription subscriptionList = null;
      try {
        subscriptionList = subscriptionService.findById(id);
      } catch (ResourceNotFoundException ex) {
        model.addAttribute("errorMessage", "package not found");
      }
      model.addAttribute("package", subscriptionList);
      return "subscriptions.html";
  }
  
  @GetMapping(value = {"/packages/add"})
  public String showAddpackage(Model model) {
      Subscription subscription = new Subscription();
      model.addAttribute("add", true);
      model.addAttribute("package", subscription);

      return "subscriptions-edit.html";
  }

  @PostMapping(value = "/packages/add")
  public String addpackage(Model model, @ModelAttribute("package") Subscription subscription) {        
      try {
        subscriptionService.save(subscription);
          return "redirect:/subscription";  
      } catch (Exception ex) {
          // log exception first, 
          // then show error
          String errorMessage = ex.getMessage();
          logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
          //model.addAttribute("member", member);
          model.addAttribute("add", true);
          return "subscriptions-edit.html";
      }        
  }
 
  
  @GetMapping(value = {"/packages/{id}/edit"})
  public String showEditmember(Model model, @PathVariable long id) {
      Subscription subscription = null;
      try {
        subscription = subscriptionService.findById(id);
      } catch (ResourceNotFoundException ex) {
          model.addAttribute("errorMessage", "member not found");
      }
      model.addAttribute("add", false);
      model.addAttribute("package", subscription);
      return "subscriptions-edit.html";
  }

  @PostMapping(value = {"/packages/{id}/edit"})
  public String updatepackage(Model model,
          @PathVariable long id,
          @ModelAttribute("package") Subscription subscription) {        
      try {
        subscriptionService.update(subscription);
          return "redirect:/subscriptions";  
      } catch (Exception ex) {
          // log exception first, 
          // then show error
          String errorMessage = ex.getMessage();
          logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
          model.addAttribute("add", false);
          return "subscriptions-edit.html";
      }
  }

  @GetMapping(value = {"/packages/{id}/delete"})
  public String showDeletememberById(
      Model model, @PathVariable long id) {
      Member subscription = null;
      try {
        subscription = memberService.findById(id);
      } catch (ResourceNotFoundException ex) {
          model.addAttribute("errorMessage", "package not found");
      }
      model.addAttribute("allowDelete", true);
      model.addAttribute("package", subscription);
      try {
        memberService.deleteById(id);
          return "redirect:/subscription";
      } catch (ResourceNotFoundException ex) {
          String errorMessage = ex.getMessage();
          logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
      }
      return "redirect:/subscriptions";
  }
}