package mumsched.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mumsched.model.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long>{
	
}
