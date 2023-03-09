package fa.training.controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fa.training.models.Course;
import fa.training.service_.CourseService;

@RequestMapping("/course")

public class CourseController {
	
	@Autowired
	private CourseService courseService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/list")
	public String getCourse(ModelMap model) {
		List<Course> courses = courseService.getCourses();
		model.addAttribute("courses", courses);
		return "list-courses";
	}
	
	@GetMapping("/detail/{courseId}")
	public String getCourse(ModelMap model, @PathVariable("courseId") String courseId) {
		return "redirect:/student" + courseId +"/list";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Course course = new Course();
		model.addAttribute("course", course);
		return "course-form";
	}
	
	@GetMapping("/delete")
	public String deleteCourse(ModelMap model, String id) {
		courseService.deleteCourse(id);
		return "redirect:/course/list";
	}
	
	@PostMapping(value = "/save-course")
	public String saveCustomer(@Valid @ModelAttribute("course") Course course
			, BindingResult bindingResult , ModelMap modelMap) {
		if (bindingResult.hasErrors()) {
			return "course-form";
		} else {
			courseService.saveCourse(course);
			List<Course> courses = courseService.getCourses();
			modelMap.addAttribute("courses", courses);
			return "list-courses";
		}
	}
	
	public String showFormForStringUpdate(@RequestParam("courseId") String id, ModelMap model) {
		Course course = courseService.getCourseById(id);
		
		if (course == null) {
			return "404";
		}
		
		model.addAttribute("course", course);
		return "course-form";
	}
	
	
}
