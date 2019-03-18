package mumsched.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mumsched.model.CoursePrerequisite;

@Repository
public interface CoursePrerequisiteRepo extends JpaRepository<CoursePrerequisite, Long>{

}
