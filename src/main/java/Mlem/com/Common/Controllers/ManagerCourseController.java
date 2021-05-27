package Mlem.com.Common.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Mlem.com.Common.Services.UserService;

@Controller
@RequestMapping("/admin")
public class ManagerCourseController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/setEnableAccount",method = RequestMethod.GET)
	public String setEnableAccount(int id,boolean enable) {
		
		 userService.updateEnableUser(id,enable);
		 return "redirect:/admin/";
	}
	
	@RequestMapping(value = "/setRoleAccount",method = RequestMethod.GET)
	public String setRoleAccount(int id,int roleid) {
		
		 userService.updateRoleUser(id,roleid);
		 return "redirect:/admin/";
	}
	
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String Admin() {		
		 return "fragments";
	}
	
	@RequestMapping(value = "/deleteAccount",method = RequestMethod.GET)
	public String deleteAccount(int id) {
		
		 userService.deleteUser(id);
		 return "redirect:/admin/";
	}

}
