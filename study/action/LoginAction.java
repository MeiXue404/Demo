package com.study.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.study.model.User;
import com.study.service.LoginService;

@Controller("LoginAction")
public class LoginAction {
	@Resource(name="LoginService")
	LoginService service;
	@Resource
	HttpServletRequest request;
	@RequestMapping("/Uer/Login")
	public String Login(@RequestParam String username, @RequestParam String userpwd,HttpServletRequest request){
		String SuccessUrl="/Success.jsp";
		String falseUrl="/Login.jsp";
		try {
			return service.Login(username,userpwd);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}
		return falseUrl;
	}
	
	@RequestMapping("ToLogin")
	public ModelAndView ToLogin(){
		return new ModelAndView("WEB-INF/Login.jsp");
	}

	//参数绑定
	@RequestMapping("/bangDing")
	public void bangDing(User user){
		System.out.println("打印User数据:");
		System.out.println(user.getUserName());
		System.out.println(user.getRealName());
		System.out.println(user.getUserPassWord());
		System.out.println(user.getEmail());
		System.out.println(user.getAge());
	}
}
