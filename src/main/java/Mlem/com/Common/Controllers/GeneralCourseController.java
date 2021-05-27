package Mlem.com.Common.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Mlem.com.Common.Entity.Categories;
import Mlem.com.Common.Entity.GeneralCourse;
import Mlem.com.Common.Entity.LevelCourse;
import Mlem.com.Common.Entity.User;
import Mlem.com.Common.Services.CategoriesService;
import Mlem.com.Common.Services.GeneralCourseService;
import Mlem.com.Common.Services.LevelCourseService;
import Mlem.com.Common.Services.UserService;

@Controller
public class GeneralCourseController {

	@Autowired
	UserService userService;
	@Autowired
	CategoriesService categoriesService;
	@Autowired
	LevelCourseService levelCourseService;
	@Autowired

	private GeneralCourseService generalCourseService;

//	@RequestMapping("/")
//	public String index(Model model) {
//		List<GeneralCourse> generalCourse = generalCourseService.getAllGeneralCourse();
//
//		model.addAttribute("GeneralCourse", generalCourse);
//
//		return "index";
//	}

	@RequestMapping("/newGeneralCourse")
	public String NewGeneralCoursePage(
			@CookieValue(value = "MY_USER", defaultValue = "defaultCookieValue") String userCookie, Model model) {
		ArrayList<Categories> cateName = (ArrayList<Categories>) categoriesService.getAllCategories();
		ArrayList<LevelCourse> levelName = (ArrayList<LevelCourse>) levelCourseService.getAllLevelCourse();
		model.addAttribute("levelName",levelName);
		model.addAttribute("cateName",cateName);
		model.addAttribute("user", userService.getAccount(userCookie));
		return "create_new_course";
	}

	@RequestMapping(value = "/generalCourse/create", method = RequestMethod.POST)
	@ResponseBody
	public GeneralCourse addGeneralCourse(@RequestBody GeneralCourse generalCourse) {
		
		
		System.out.println(generalCourse);
	
		return generalCourse;
	}

	@RequestMapping(value = "/generalCourse/edit", method = RequestMethod.GET)
	public String editGeneralCourse(@RequestParam("id") int generalCourseId, Model model) {
		Optional<GeneralCourse> userEdit = generalCourseService.findGeneralCourseById(generalCourseId);
		userEdit.ifPresent(user -> model.addAttribute("user", user));
		return "editGeneralCourse";
	}

	@RequestMapping(value = "/generalCourse/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@Validated GeneralCourse generalCourse, BindingResult result, RedirectAttributes redirect) {
		generalCourseService.saveGeneralCourse(generalCourse);
		redirect.addFlashAttribute("success", "Saved generalCourse successfully!");
		return "redirect:/generalCourse";
	}

	@RequestMapping(value = "/generalCourse/delete", method = RequestMethod.GET)
	public String deleteGeneralCourse(@RequestParam("id") int generalCourseId, Model model) {
		generalCourseService.deleteGeneralCourse(generalCourseId);
		return "redirect:/";
	}

//	@RequestMapping(value = "/newGeneralCourse", method = RequestMethod.POST)
//	@ResponseBody
//	public String NewGeneralCoursePage(@RequestBody GeneralCourse course) {
////		return generalCourseService.saveGeneralCourse(course);
//		return "create_new_course";
//
//	}

}
