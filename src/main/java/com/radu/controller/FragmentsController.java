package com.radu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.radu.configuration.AuthenticationFacade;
import com.radu.model.User;
import com.radu.service.UserService;

@Controller
public class FragmentsController {
    private final UserService userService;
    private final AuthenticationFacade authenticationFacade;

    public FragmentsController(UserService userService, AuthenticationFacade authenticationFacade) {
        this.userService = userService;
        this.authenticationFacade = authenticationFacade;
    }

    @GetMapping("/fragments")
    public String getHome(Model model) {
    String username = authenticationFacade.getAuthenticatedUsername();
    User user = userService.findByUsername(username);
    if (user != null) {
        model.addAttribute("user", user);
    }
    
    return "fragments.html";
    }


    @GetMapping("/markup")
    public String markupPage() {
        return "markup.html";
    }

    @GetMapping("/params")
    public String paramsPage() {
        return "params.html";
    }
}
	