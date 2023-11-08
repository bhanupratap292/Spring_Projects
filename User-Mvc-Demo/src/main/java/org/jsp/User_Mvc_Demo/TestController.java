package org.jsp.User_Mvc_Demo;

import javax.persistence.Persistence;

import org.jsp.User_Mvc_Demo.dao.UserDao;
import org.jsp.User_Mvc_Demo.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	@RequestMapping("/test")
	@ResponseBody
	public String hello() {
		return "Hello from Test class";
	}

	@RequestMapping("/test-db-cfg")
	@ResponseBody
	public String testDatabaseConfiguration() {
		return Persistence.createEntityManagerFactory("dev").toString();
	}
	
	
}


