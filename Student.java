package fa.training.models;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import fa.training.utils.Validator;

@Entity
@Table(name = "student")
public class Student implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "student_id")
	@NotNull(message = "require!")
	@Pattern(regexp = Validator.VALID_STUDENT_ID_REGEX, message = "A student's id has six digits!")
	private String id;
	
	@Column(name = "student_name")
	@NotNull(message = "require!")
	private String name;
	
	@Column(name = "phone")
	@Pattern(regexp = Validator.VALID_PHONE_REGEX, message = "A phonenumber must be at least 10 digits!")
	@NotNull(message = "Required!")
	private String phone;
	
	@Column(name = "gender")
	@NotNull(message = "required!")
	private String gender;
	
	@Column(name = "gpa")
	@Min(value = 0, message = "A Student's GPA must be greater than or equal to zero!")
	@Max(value = 10, message = "A Student's GPA must be less than or equal to ten!")
	private double gpa;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "course_id")
	private Course course;

	public Student(
			@NotNull(message = "require!") @Pattern(regexp = "^\\d{6}", message = "A student's id has six digits!") String id,
			@NotNull(message = "require!") String name,
			@Pattern(regexp = "^\\d{10}", message = "A phonenumber must be at least 10 digits!") @NotNull(message = "Required!") String phone,
			@NotNull(message = "required!") String gender,
			@Min(value = 0, message = "A Student's GPA must be greater than or equal to zero!") @Max(value = 10, message = "A Student's GPA must be less than or equal to ten!") double gpa) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.gender = gender;
		this.gpa = gpa;
	}

	public Student() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return String.format("Student [id=%, name=%, phone=%, gender=%, gpa=%]", id, name, phone, gender, gpa);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null)? 0: id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
	
	
}
