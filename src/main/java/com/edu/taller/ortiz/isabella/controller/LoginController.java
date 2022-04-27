package com.edu.taller.ortiz.isabella.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	    @GetMapping("/login")
	    public String login(){
	        return "loginpage";
	        
	    }

	    @GetMapping("/logout")
	    public String logout(){
	        return "logout";
	        
	    }

	    @GetMapping("/access-denied")
	    public String accessDenied(){
	        return "access-denied";
	        
	    }
}
