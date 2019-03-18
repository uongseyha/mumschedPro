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

import mumsched.repo.CourseRepo;
import mumsched.model.*;
import java.lang.*;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class CourseController {
	
	@Autowired
	private CourseRepo courseRepo;
	
	//=== Get All Courses ===
	@GetMapping("/course")
	public List<Course> getAllCourses() {
		return courseRepo.findAll();
	}
	
	//=== Get Course by ID  ===
	@GetMapping("/course/{id}")
	public Course getCourseById(@PathVariable long id){
		Optional<Course> course = courseRepo.findById(id);

		if (!course.isPresent())  
			ResponseEntity.notFound().build();

		return course.get();
	}
	
	//=== Delete Course by ID ===
	@DeleteMapping("/course/{id}")
	public void deleteCourse(@PathVariable long id) {
		courseRepo.deleteById(id);
	}
	
	//=== Create new Course =======
	@PostMapping("/course")
	public Course createCourse(@RequestBody Course course) {
		return courseRepo.save(course);

	}
	
	//=== Update Course by ID=======
	@PutMapping("/course/{id}")
	public ResponseEntity<Object> updateCourse(@RequestBody Course course, @PathVariable long id) {

		Optional<Course> courseOptional = courseRepo.findById(id);

		if (!courseOptional.isPresent())
			return ResponseEntity.notFound().build();

		course.setId(id);
		Course updateCourse=courseRepo.save(course);
		
		return ResponseEntity.ok().body(updateCourse);
	}
}
