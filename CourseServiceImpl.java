package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import fa.training.dao_.CourseDAO;
import fa.training.models.Course;

public class CourseServiceImpl implements CourseDAO{
	@Autowired
	private CourseDAO courseDAO;

	@Override
	@Transactional
	public List<Course> getCourses() {
		return courseDAO.getCourses();
	}

	@Override
	@Transactional
	public void saveCourse(Course course) {
		courseDAO.saveCourse(course);
		
	}

	@Override
	@Transactional
	public Course getCourseById(String id) {
		return courseDAO.getCourseById(id);
	}

	@Override
	@Transactional
	public void deleteCourse(String id) {
		courseDAO.deleteCourse(id);
		
	}
}
