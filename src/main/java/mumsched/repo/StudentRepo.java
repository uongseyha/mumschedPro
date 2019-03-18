package mumsched.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mumsched.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long>{
	
}
