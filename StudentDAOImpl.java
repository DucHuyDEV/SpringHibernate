package fa.training.dao.impl;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import fa.training.dao_.StudentDAO;
import fa.training.models.Student;

public class StudentDAOImpl implements StudentDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public StudentDAOImpl() {
		super();
	}

	@Override
	public List<Student> getStudents() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Student> query = currentSession.createQuery("from Student", Student.class);
		List<Student> students = query.getResultList();
		return students;
	}

	@Override
	public void saveStudent(Student student) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(student);
	}

	@Override
	public Student getStudentById(String id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Student student = currentSession.get(Student.class, id);
		return student;
	}

	@Override
	public void deleteStudent(String Id) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.createQuery("delete from Student where id=:studentId").setParameter("studentId", Id).executeUpdate();
		
	}
	
	
}
