package fa.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import fa.training.dao_.StudentDAO;
import fa.training.models.Student;

public class StudentServiceImpl implements StudentDAO{
	
	@Autowired
	private StudentDAO studentDAO;
	
	@Override
	@Transactional
	public List<Student> getStudents() {
		return studentDAO.getStudents();
	}

	@Override
	@Transactional
	public void saveStudent(Student student) {
		studentDAO.saveStudent(student);
	}

	@Override
	@Transactional
	public Student getStudentById(String id) {
		return studentDAO.getStudentById(id);
	}

	@Override
	@Transactional
	public void deleteStudent(String Id) {
		studentDAO.deleteStudent(Id);
		
	}
	
}
