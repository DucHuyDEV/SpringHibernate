package fa.training.dao.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Session;
import org.hibernate.SessionFactory;





import fa.training.dao_.CourseDAO;
import fa.training.models.Course;

public class CourseDAOImpl implements CourseDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public CourseDAOImpl() {
		super();
	}

	@Override
	public List<Course> getCourses() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Course> theQuery = currentSession.createQuery("from Course", Course.class);
		
		List<Course> courses = theQuery.getResultList();
		return courses;
	}

	@Override
	public void saveCourse(Course course) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(course);
		
	}

	@Override
	public Course getCourseById(String id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Course tempCourse = currentSession.get(Course.class, id);
		return tempCourse;
	}

	@Override
	public void deleteCourse(String id) {
		Session currentSession = sessionFactory.getCurrentSession();
	    currentSession.createQuery("delete from Course where id =:courseId").setParameter("courseId", id).executeUpdate();
	}

}
