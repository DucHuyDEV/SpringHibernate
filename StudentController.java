package fa.training.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

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
import fa.training.models.Student;
import fa.training.service_.CourseService;
import fa.training.service_.StudentService;


@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CourseService courseService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	
	@RequestMapping("{courseId}/list")
	private String listStudents(ModelMap model,@PathVariable("courseId") String courseId) {
		Course course = courseService.getCourseById(courseId);
		
		if (course == null) {
			return "404";
		}
		
		model.addAttribute("course", course);
		model.addAttribute("studentId", course.getStudents());
		return "list-students";
	}
	
	@PostMapping("{courseId}/save-student")
	public String saveStudent(@Valid @ModelAttribute("student") Student student, 
			BindingResult bindingResult, ModelMap model, 
			@PathVariable("courseId") String courseId) {
		Course course = courseService.getCourseById(courseId);
		
		if (course == null) {
			return "404";
		} else if (bindingResult.hasErrors()) {
			model.addAttribute("course", course);
			return "student-form";
		} else {
			if (course.getErollment() <= course.getStudents().size() 
					&& studentService.getStudentById(student.getId()) == null) {
				model.addAttribute("message", "The course is full!");
				return "student-form";
			} else {
				student.setCourse(course);
				studentService.saveStudent(student);
				return "redirect:/student/" + courseId + "/list";
			}
		}
	}
	
	@GetMapping("/{courseId}/showFormforUpdate")
	 public String showFormForUpdate(
			  @PathParam("studentId") String studentId, ModelMap model, 
			  @PathVariable("courseId") String courseId) {
		 Course course = courseService.getCourseById(courseId);
		 Student student = studentService.getStudentById(studentId);
		 if (course == null || student == null) {
				return "404";
			}
			model.addAttribute("course", course);
			model.addAttribute("student", student);


		 return "student-form";
	}
	
	@GetMapping("/{courseId}/showFormForAdd")
	public String showFormForAdd(Model model, @PathVariable("courseId") String courseId) {
		Course course = courseService.getCourseById(courseId);
		
		if (course == null) {
			return "404";
		}
		model.addAttribute("course", course);
		model.addAttribute("student", new Student());
		return "student-form";
	}
	
	@GetMapping("{courseId}/delete")
	public String deleteCustomer(@RequestParam("studentId") String theId, ModelMap theModel,
			@PathVariable("courseId") String courseId) {

		studentService.deleteStudent(theId);
		Course theCourse = courseService.getCourseById(courseId);

		if (theCourse == null) {
			return "404";
		}
		return "redirect:/student/" + courseId + "/list";
	}

}
