package mumsched.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mumsched.model.FacultyCourse;

@Repository
public interface FacultyCourseRepo extends JpaRepository<FacultyCourse, Long>{
	
}
