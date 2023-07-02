package com.radu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.radu.service.HistoryService;
import com.radu.exception.ResourceNotFoundException;
import com.radu.model.History;
import com.radu.model.Member;
import com.radu.model.Subscription;
import com.radu.service.MemberService;
import com.radu.service.SubscriptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HistoryController {
  
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final int ROW_PER_PAGE = 5;

  @Autowired
  private MemberService memberService;

  @Autowired
  private HistoryService historyService;

  @Autowired
  private SubscriptionService subscriptionService;
  
  @GetMapping("/history")
  public String gethistory(Model model, 
		@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		List<History> history = historyService.findAll(pageNumber, ROW_PER_PAGE);
		long count = historyService.count();
		boolean hasPrev = pageNumber > 1;
		boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
    List<Member> members = memberService.findAll();
		model.addAttribute("history", history);
    model.addAttribute("members", members);
		model.addAttribute("hasPrev", hasPrev);
		model.addAttribute("prev", pageNumber - 1);
		model.addAttribute("hasNext", hasNext);
		model.addAttribute("next", pageNumber + 1);
		return "history.html";
  }

  @GetMapping(value = "/history/{id}")
  public String gethistoryById(Model model, @PathVariable long id) {
      History history = null;
      try {
        history = historyService.findById(id);
      } catch (ResourceNotFoundException ex) {
        model.addAttribute("errorMessage", "history not found");
      }
      model.addAttribute("history", history);
      return "history.html";
  }
  

  @GetMapping(value = {"/history/{idmember}/edit"})
  public String showEdithistory(Model model, @PathVariable long idmember) {
      Member member = null;
      try {
          member = memberService.findById(idmember);
      } catch (ResourceNotFoundException ex) {
          model.addAttribute("errorMessage", "member not found");
      }
  	  List<Subscription> subscriptionList = subscriptionService.findAll();
      History history = null;
      try {
         history = historyService.findLastByIdmember(idmember);
      } catch (ResourceNotFoundException ex) {
          model.addAttribute("errorMessage", "history not found");
      }
      model.addAttribute("add", false);
      model.addAttribute("member", member);
      model.addAttribute("package", subscriptionList);
      model.addAttribute("history", history);
      return "history-edit.html";
  }

  @PostMapping(value = {"/history/{idmember}/edit"})
  public String updateHistory(Model model, @PathVariable long idmember, 
		  @ModelAttribute("history") History history) {        
      try {
    	  historyService.update(history);
          return "redirect:/history";  
      } catch (Exception ex) {
          // log exception first, 
          // then show error
          String errorMessage = ex.getMessage();
          logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
          model.addAttribute("add", false);
          return "history-edit.html";
      }
  }
  
  @GetMapping(value = {"/history/{id}/delete"})
  public String showDeletehistoryById(
        Model model, @PathVariable long id) {
        History history = null;
      try {
        history = historyService.findById(id);
      } catch (ResourceNotFoundException ex) {
          model.addAttribute("errorMessage", "history not found");
      }
      model.addAttribute("allowDelete", true);
      model.addAttribute("history", history);
      try {
        historyService.deleteById(id);
          return "redirect:/history";
      } catch (ResourceNotFoundException ex) {
          String errorMessage = ex.getMessage();
          logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
      }
      return "redirect:/history";
  }
  
 

}