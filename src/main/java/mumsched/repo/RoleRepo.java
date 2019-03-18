package mumsched.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mumsched.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long>{
	
}
