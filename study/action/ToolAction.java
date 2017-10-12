package com.study.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("ToolAction")
public class ToolAction {
	@RequestMapping("/goPage")
	public String goPage(@RequestParam String pageName){	
		return pageName;
	}
}
