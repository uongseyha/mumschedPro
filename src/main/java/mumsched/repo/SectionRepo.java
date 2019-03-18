package mumsched.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mumsched.model.Section;

@Repository
public interface SectionRepo extends JpaRepository<Section, Long>{
	
}
