package mumsched.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mumsched.model.EntryBlock;

@Repository
public interface EntryBlockRepo extends JpaRepository<EntryBlock, Long>{
	
}
