package com.study.action;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.model.User;

@Controller("RegisterAction")
public class RegisterAction {
	
	@Resource
	HttpServletRequest request;
	
	//前往注册
	@RequestMapping("GoRegister")
	public String GoRegiater(){
		System.out.println("进入Action");
		return "/WEB-INF/User/Register.jsp";
	}
	
	//用户注册
	@RequestMapping("Register")
	public String Register(@Valid User user,BindingResult result){
		System.out.println("进入RegisterAction");
		if (result.hasErrors()) {
			List<FieldError> fieldErrors=result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				request.setAttribute("Err_"+fieldError.getField(), fieldError.getDefaultMessage());
			}
			System.out.println("结束校验");
			return "/WEB-INF/User/Register.jsp";
		}
		//省略相关的注册逻辑
		return "/WEB-INF/User/Message.jsp";
	}
}
