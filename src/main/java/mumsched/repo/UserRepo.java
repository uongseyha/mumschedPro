package mumsched.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mumsched.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
//	@Query("select s from User s where s.userName= :userName")
//	public User findUserByNamePassword(@Param("userName") String userName);
	
	//public List<User> findUserByNamePassword(String userName,String userPassword);
	
	
}
