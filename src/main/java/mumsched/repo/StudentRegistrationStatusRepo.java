package mumsched.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mumsched.model.StudentRegistrationStatus;

@Repository
public interface StudentRegistrationStatusRepo extends JpaRepository<StudentRegistrationStatus, Long>{
	
}
