package com.study.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.model.User;

@Controller("JsonAction")
public class JsonAction {
	@RequestMapping("/textJSon")
	public @ResponseBody User textJSon(@RequestBody User user,HttpServletResponse response,HttpServletRequest request){
		System.out.println("进入JsonAction");
		
		Cookie[] cookies=request.getCookies();//获取请求中的cookie
		if (cookies!=null) {
			for (Cookie cookie : cookies) {
				System.out.println("打印请求中的cookie");
				System.out.println("Name:"+cookie.getName());
				System.out.println("Value:"+cookie.getValue());
				System.out.println("Comment:"+cookie.getComment());
				System.out.println("Domian:"+cookie.getDomain());
				System.out.println("MaxAge:"+cookie.getMaxAge());
				System.out.println("Path:"+cookie.getPath());
				System.out.println("Class:"+cookie.getClass());
				System.out.println("Version:"+cookie.getVersion());
				System.out.println("Sercure:"+cookie.getSecure());
				
			}
		}
		Cookie cookie=new Cookie("mycookie", "mima");		
		cookie.setMaxAge(-10000);  //expires;24小时过期
		cookie.setHttpOnly(false);  //javaScript不能处理
		cookie.setSecure(false); //如果为true，则支持HTTPS协议	
		cookie.setPath("/SpringMvc01/");  // web应用路径下(包括子路径)都将携带cookie
		response.addCookie(cookie); 
		
		request.setAttribute("user", user);		
		return user;
	}
}
