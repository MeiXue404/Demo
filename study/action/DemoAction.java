package com.study.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller("DemoAction")
public class DemoAction {
	//测试跳转
	@RequestMapping("/textRedirect")
	public void TextRedirect(HttpServletResponse response,HttpServletRequest request){
		
			System.out.println("进入重定向");
			String path=request.getRequestURI();
			System.out.println("打印请求路径:"+path);
			//request.getRequestDispatcher("toRedirect.action");
			try {
				response.sendRedirect("Success.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
