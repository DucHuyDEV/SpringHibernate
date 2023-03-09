package fa.training.dao_;

import java.util.List;

import fa.training.models.Student;

public interface StudentDAO {
	public List<Student> getStudents();
	public void saveStudent(Student student);
	public Student getStudentById(String id);
	public void deleteStudent(String Id);
}
