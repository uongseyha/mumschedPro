package mumsched.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import mumsched.repo.CoursePrerequisiteRepo;
import mumsched.model.*;
import java.lang.*;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class CoursePrerequisiteController {
	
	@Autowired
	private CoursePrerequisiteRepo coursePrerequisiteRepo;
	
	//=== Get All CoursePrerequisites ===
	@GetMapping("/coursePrerequisite")
	public List<CoursePrerequisite> getAllCoursePrerequisites() {
		return coursePrerequisiteRepo.findAll();
	}
	
	//=== Get CoursePrerequisite by ID  ===
	@GetMapping("/coursePrerequisite/{id}")
	public CoursePrerequisite getCoursePrerequisiteById(@PathVariable long id){
		Optional<CoursePrerequisite> coursePrerequisite = coursePrerequisiteRepo.findById(id);

		if (!coursePrerequisite.isPresent())  
			ResponseEntity.notFound().build();

		return coursePrerequisite.get();
	}
	
	//=== Delete CoursePrerequisite by ID ===
	@DeleteMapping("/coursePrerequisite/{id}")
	public void deleteCoursePrerequisite(@PathVariable long id) {
		coursePrerequisiteRepo.deleteById(id);
	}
	
	//=== Create new CoursePrerequisite =======
	@PostMapping("/coursePrerequisite")
	public CoursePrerequisite createCoursePrerequisite(@RequestBody CoursePrerequisite coursePrerequisite) {
		return coursePrerequisiteRepo.save(coursePrerequisite);

	}
	
	//=== Update CoursePrerequisite by ID=======
	@PutMapping("/coursePrerequisite/{id}")
	public ResponseEntity<Object> updateCoursePrerequisite(@RequestBody CoursePrerequisite coursePrerequisite, @PathVariable long id) {

		Optional<CoursePrerequisite> coursePrerequisiteOptional = coursePrerequisiteRepo.findById(id);

		if (!coursePrerequisiteOptional.isPresent())
			return ResponseEntity.notFound().build();

		coursePrerequisite.setId(id);
		CoursePrerequisite updateCoursePrerequisite=coursePrerequisiteRepo.save(coursePrerequisite);
		
		return ResponseEntity.ok().body(updateCoursePrerequisite);
	}
}
