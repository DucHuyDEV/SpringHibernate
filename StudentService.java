package fa.training.service_;

import java.util.List;

import fa.training.models.Student;

public interface StudentService {
	public List<Student> getStudents();
	public void saveStudent(Student student);
	public Student getStudentById(String id);
	public void deleteStudent(String id);
}
