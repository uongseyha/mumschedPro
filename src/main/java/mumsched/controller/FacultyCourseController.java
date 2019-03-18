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

import mumsched.repo.FacultyCourseRepo;
import mumsched.model.*;
import java.lang.*;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class FacultyCourseController {
	
	@Autowired
	private FacultyCourseRepo facultyCourseRepo;
	
	//=== Get All FacultyCourses ===
	@GetMapping("/facultyCourse")
	public List<FacultyCourse> getAllFacultyCourses() {
		return facultyCourseRepo.findAll();
	}
	
	//=== Get FacultyCourse by ID  ===
	@GetMapping("/facultyCourse/{id}")
	public FacultyCourse getFacultyCourseById(@PathVariable long id){
		Optional<FacultyCourse> facultyCourse = facultyCourseRepo.findById(id);

		if (!facultyCourse.isPresent())  
			ResponseEntity.notFound().build();

		return facultyCourse.get();
	}
	
	//=== Delete FacultyCourse by ID ===
	@DeleteMapping("/facultyCourse/{id}")
	public void deleteFacultyCourse(@PathVariable long id) {
		facultyCourseRepo.deleteById(id);
	}
	
	//=== Create new FacultyCourse =======
	@PostMapping("/facultyCourse")
	public FacultyCourse createFacultyCourse(@RequestBody FacultyCourse facultyCourse) {
		return facultyCourseRepo.save(facultyCourse);

	}
	
	//=== Update FacultyCourse by ID=======
	@PutMapping("/facultyCourse/{id}")
	public ResponseEntity<Object> updateFacultyCourse(@RequestBody FacultyCourse facultyCourse, @PathVariable long id) {

		Optional<FacultyCourse> facultyCourseOptional = facultyCourseRepo.findById(id);

		if (!facultyCourseOptional.isPresent())
			return ResponseEntity.notFound().build();

		facultyCourse.setId(id);
		FacultyCourse updateFacultyCourse=facultyCourseRepo.save(facultyCourse);
		
		return ResponseEntity.ok().body(updateFacultyCourse);
	}
}
