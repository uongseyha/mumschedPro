package mumsched.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mumsched.model.Block;

@Repository
public interface BlockRepo extends JpaRepository<Block, Long>{
	
}
