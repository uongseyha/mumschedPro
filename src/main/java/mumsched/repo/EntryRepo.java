package mumsched.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mumsched.model.Entry;

@Repository
public interface EntryRepo extends JpaRepository<Entry, Long>{
	
}
