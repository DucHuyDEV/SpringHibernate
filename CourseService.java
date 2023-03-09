package fa.training.service_;

import java.util.List;

import fa.training.models.Course;

public interface CourseService {
	public List<Course> getCourses();
	public void saveCourse(Course cosCourse);
	public Course getCourseById(String id);
	public void deleteCourse(String id);
}
