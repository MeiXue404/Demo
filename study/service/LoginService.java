package com.study.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service("LoginService")
public class LoginService {

	public String Login(String username, String uesrpwd) {
		return "/Success.jsp";
		
	}

	
	
}