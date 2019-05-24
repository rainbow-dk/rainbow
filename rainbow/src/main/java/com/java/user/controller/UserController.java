package com.java.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.user.entity.User;
import com.java.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	public UserService userService;
	
	
	@RequestMapping("/login")
	public String login(){
		return "/login";
	}
	
	
	@RequestMapping("/doLogin")
	public String index(HttpServletRequest request,User user){
		
		User activeUser = userService.selectUser(user);
		return "/login";
	}

}
