package mumsched.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mumsched.model.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long>{
	
}
