package mumsched.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mumsched.model.Faculty;

@Repository
public interface FacultyRepo extends JpaRepository<Faculty, Long>{
	
}
