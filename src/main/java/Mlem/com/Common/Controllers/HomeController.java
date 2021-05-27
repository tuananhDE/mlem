package Mlem.com.Common.Controllers;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Mlem.com.Common.Entity.User;
import Mlem.com.Common.Repository.UserRepository;
import Mlem.com.Common.Services.UserService;


 

@Controller
public class HomeController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository repo;
	@RequestMapping("/")
	public String Home(@CookieValue(value = "MY_USER", defaultValue = "defaultCookieValue") String userCookie,
			Model model) {
		model.addAttribute("user", userService.getAccount(userCookie));
		
	 return "index";
	}
	@RequestMapping(value = "/res", method  = RequestMethod.POST)
	public String ACC() {
		return "home";
		
	}
	@RequestMapping(value = "/fragments", method  = RequestMethod.GET)
	public String a() {
		return "fragments";
		
	}
}
