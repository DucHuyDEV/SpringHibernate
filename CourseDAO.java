package fa.training.dao_;


import java.util.List;

import fa.training.models.Course;

public interface CourseDAO {
	public List<Course> getCourses();
	public void saveCourse(Course course);
	public Course getCourseById(String id);
	public void deleteCourse(String id);
}
