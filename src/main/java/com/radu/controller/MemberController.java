package com.radu.controller;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.radu.service.HistoryService;
import com.radu.service.MemberService;
import com.radu.service.SubscriptionService;
import com.radu.exception.ResourceNotFoundException;
import com.radu.model.History;
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
public class MemberController {
    
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  
  private final int ROW_PER_PAGE = 5;
  
  @Autowired
  private MemberService memberService;
  
  @Autowired
  private HistoryService historyService;

  @Autowired
  private SubscriptionService subscriptionService;
    
  @GetMapping("/members")
  public String getMember(Model model,
		@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		List<Member> members = memberService.findAll(pageNumber, ROW_PER_PAGE);
		long count = memberService.count();
		boolean hasPrev = pageNumber > 1;
		boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
		model.addAttribute("members", members);
		model.addAttribute("hasPrev", hasPrev);
		model.addAttribute("prev", pageNumber - 1);
		model.addAttribute("hasNext", hasNext);
		model.addAttribute("next", pageNumber + 1);
		return "members.html";
  }

  @GetMapping(value = "/members/{id}")
  public String getmemberById(Model model, @PathVariable long id) {
      Member member = null;
      try {
        member = memberService.findById(id);
      } catch (ResourceNotFoundException ex) {
        model.addAttribute("errorMessage", "member not found");
      }
      model.addAttribute("member", member);
      return "members.html";
  }
  
  @GetMapping(value = {"/members/add"})
  public String showAddmember(Model model) {
      Member member = new Member();
      model.addAttribute("add", true);
      model.addAttribute("member", member);

      return "members-edit.html";
  }

  @PostMapping(value = "/members/add")
  public String addmember(Model model, @ModelAttribute("member") Member member) {        
      try {
        memberService.save(member);
          return "redirect:/members";  
      } catch (Exception ex) {
          // log exception first, 
          // then show error
          String errorMessage = ex.getMessage();
          logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
          //model.addAttribute("member", member);
          model.addAttribute("add", true);
          return "members-edit.html";
      }        
  }
 
  
  @GetMapping(value = {"/members/{id}/edit"})
  public String showEditmember(Model model, @PathVariable long id) {
      Member member = null;
      try {
          member = memberService.findById(id);
      } catch (ResourceNotFoundException ex) {
          model.addAttribute("errorMessage", "member not found");
      }
      model.addAttribute("add", false);
      model.addAttribute("member", member);
      return "members-edit.html";
  }

  @PostMapping(value = {"/members/{id}/edit"})
  public String updatemember(Model model,
          @PathVariable long id,
          @ModelAttribute("member") Member member) {        
      try {
        memberService.update(member);
          return "redirect:/members";  
      } catch (Exception ex) {
          // log exception first, 
          // then show error
          String errorMessage = ex.getMessage();
          logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
          model.addAttribute("add", false);
          return "members-edit.html";
      }
  }

  @GetMapping(value = {"/members/{id}/delete"})
  public String showDeletememberById(
      Model model, @PathVariable long id) {
      Member member = null;
      try {
          member = memberService.findById(id);
      } catch (ResourceNotFoundException ex) {
          model.addAttribute("errorMessage", "member not found");
      }
      model.addAttribute("allowDelete", true);
      model.addAttribute("member", member);
      try {
        memberService.deleteById(id);
          return "redirect:/members";
      } catch (ResourceNotFoundException ex) {
          String errorMessage = ex.getMessage();
          logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
      }
      return "redirect:/members";
  }
  
  @GetMapping(value = {"/members/{idmember}/history/edit"})
  public String showEditmemberhistory(Model model, @PathVariable long idmember) {
      Member member = null;
      try {
         member = memberService.findById(idmember);
    } catch (ResourceNotFoundException ex) {
          model.addAttribute("errorMessage", "Member not found");
      }
  	  List<Subscription> subscriptionList = subscriptionService.findAll();
      History history = null;
      try {
         history = historyService.findLastByIdmember(idmember);
      } catch (ResourceNotFoundException ex) {
         // model.addAttribute("errorMessage", "history not found");
      }
      if(history==null) {
    	history=new History();  
        model.addAttribute("add", true);
      }
      else {
    	  Calendar obj = Calendar.getInstance();
          Date datacrt = (Date) obj.getTime();
    	  if(history.getDateEnd().after(datacrt)) {
  	        model.addAttribute("add", false);
    	  }
      }
      history.setIdMember(idmember);
      history.setMemberPersonalName(member.getPersonalName());
      history.setMemberFamilyName(member.getFamilyName());
      history.setIdPackage(member.getIdSubscription());
      model.addAttribute("member", member);
      model.addAttribute("history", history);
      model.addAttribute("package", subscriptionList);
      return "members-history-edit.html";
  }
  
  @PostMapping(value = {"/members/{idmember}/history/edit"})
  public String updateMember(Model model, @PathVariable long idmember, 
		  @ModelAttribute("history") History history) {        
      try {
    	  historyService.update(history);
          return "redirect:/members";  
      } catch (Exception ex) {
          // log exception first, 
          // then show error
          String errorMessage = ex.getMessage();
          logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
          model.addAttribute("add", false);
          return "members-history-edit.html";
      }
  }
  
  @PostMapping(value = "/members/history/add")
  public String addMemberHistory(Model model, @ModelAttribute("history") History history) {        
      try {
          historyService.save(history);
          return "redirect:/members";  
      } catch (Exception ex) {
          // log exception first, 
          // then show error
          String errorMessage = ex.getMessage();
          logger.error(errorMessage);
          model.addAttribute("errorMessage", errorMessage);
          model.addAttribute("add", true);
          return "member-history-edit.html";
      }        
  }
  
}