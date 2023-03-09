package fa.training.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.stat.CollectionStatistics;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.NaturalIdCacheStatistics;
import org.hibernate.stat.QueryStatistics;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.hibernate.stat.Statistics;

import fa.training.utils.Validator;

@Entity
@Table(name = "course")
public class Course implements Serializable, Statistics{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "course_id")
	@NotNull(message = "Require!")
	@Pattern(regexp = Validator.VALID_COURSE_ID_REGEX, message = "course id: 2 upper case letter follows by 3 digits")
	private String id;
	
	@Column(name = "title")
	@NotNull(message = "require!")
	private String title;
	
	@Column(name = "credit")
	@NotNull(message = "require!")
	@Min(value = 0, message = "A credit's length must be greater than or equal to zero!")
	@Max(value = 10, message = "A credit' length must be less than or to ten!")
	private double credit;
	
	@Column(name = "enrollment")
	@NotNull(message = "require!")
	private int erollment;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "course", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	private Set<Student> students;
	
	public Course(
			@NotNull(message = "Require!") @Pattern(regexp = "[A-Z]{2}\\d{3}", message = "course id: 2 upper case letter follows by 3 digits") String id,
			@NotNull(message = "require!") String title,
			@NotNull(message = "require!") @Min(value = 0, message = "A credit's length must be greater than or equal to zero!") @Max(value = 10, message = "A credit' length must be less than or to ten!") double credit,
			@NotNull(message = "require!") int erollment, Set<Student> students) {
		super();
		this.id = id;
		this.title = title;
		this.credit = credit;
		this.erollment = erollment;
		this.students = students;
	}
	
	public Course(
			@NotNull(message = "Require!") @Pattern(regexp = "[A-Z]{2}\\d{3}", message = "course id: 2 upper case letter follows by 3 digits") String id,
			@NotNull(message = "require!") String title,
			@NotNull(message = "require!") @Min(value = 0, message = "A credit's length must be greater than or equal to zero!") @Max(value = 10, message = "A credit' length must be less than or to ten!") double credit,
			@NotNull(message = "require!") int erollment) {
		super();
		this.id = id;
		this.title = title;
		this.credit = credit;
		this.erollment = erollment;
	}

	public Course() {
		super();
	}

	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public int getErollment() {
		return erollment;
	}

	public void setErollment(int erollment) {
		this.erollment = erollment;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public void add (Student tempStudent) {
		if (students == null) {
			students = new HashSet<Student>();
		}
		students.add(tempStudent);
		tempStudent.setCourse(this);
	}
	
	public Map<String, Integer> getStatistic() {
		Map<String, Integer> stats = new HashMap<String, Integer>();
		stats.put("A", 0);
		stats.put("B", 0);
		stats.put("C", 0);
		stats.put("D", 0);
		
		for(Student student : students) {
			if (student.getGpa() >= 8.5) {
				stats.put("A", stats.get("A") + 1);
			} else if (student.getGpa() < 8.5 && student.getGpa() >= 7) {
				stats.put("B", stats.get("B") + 1);
			} else if (student.getGpa() < 7 && student.getGpa() >= 6) {
				stats.put("C", stats.get("C") + 1);
			} else {
				stats.put("D", stats.get("D") + 1);
			}
		}
		return stats;
	}
	
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EntityStatistics getEntityStatistics(String entityName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CollectionStatistics getCollectionStatistics(String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SecondLevelCacheStatistics getSecondLevelCacheStatistics(String regionName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NaturalIdCacheStatistics getNaturalIdCacheStatistics(String regionName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryStatistics getQueryStatistics(String queryString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getEntityDeleteCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getEntityInsertCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getEntityLoadCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getEntityFetchCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getEntityUpdateCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getQueryExecutionCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getQueryExecutionMaxTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getQueryExecutionMaxTimeQueryString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getQueryCacheHitCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getQueryCacheMissCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getQueryCachePutCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getNaturalIdQueryExecutionCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getNaturalIdQueryExecutionMaxTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getNaturalIdQueryExecutionMaxTimeRegion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getNaturalIdCacheHitCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getNaturalIdCacheMissCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getNaturalIdCachePutCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getUpdateTimestampsCacheHitCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getUpdateTimestampsCacheMissCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getUpdateTimestampsCachePutCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getFlushCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getConnectCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getSecondLevelCacheHitCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getSecondLevelCacheMissCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getSecondLevelCachePutCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getSessionCloseCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getSessionOpenCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCollectionLoadCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCollectionFetchCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCollectionUpdateCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCollectionRemoveCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCollectionRecreateCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getStartTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void logSummary() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isStatisticsEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setStatisticsEnabled(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getQueries() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getEntityNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getCollectionRoleNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getSecondLevelCacheRegionNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getSuccessfulTransactionCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getTransactionCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getPrepareStatementCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCloseStatementCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getOptimisticFailureCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
