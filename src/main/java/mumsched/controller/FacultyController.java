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

import mumsched.repo.FacultyRepo;
import mumsched.model.*;
import java.lang.*;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class FacultyController {
	
	@Autowired
	private FacultyRepo facultyRepo;
	
	//=== Get All Facultys ===
	@GetMapping("/faculty")
	public List<Faculty> getAllFacultys() {
		return facultyRepo.findAll();
	}
	
	//=== Get Faculty by ID  ===
	@GetMapping("/faculty/{id}")
	public Faculty getFacultyById(@PathVariable long id){
		Optional<Faculty> faculty = facultyRepo.findById(id);

		if (!faculty.isPresent())  
			ResponseEntity.notFound().build();

		return faculty.get();
	}
	
	//=== Delete Faculty by ID ===
	@DeleteMapping("/faculty/{id}")
	public void deleteFaculty(@PathVariable long id) {
		facultyRepo.deleteById(id);
	}
	
	//=== Create new Faculty =======
	@PostMapping("/faculty")
	public Faculty createFaculty(@RequestBody Faculty faculty) {
		return facultyRepo.save(faculty);

	}
	
	//=== Update Faculty by ID=======
	@PutMapping("/faculty/{id}")
	public ResponseEntity<Object> updateFaculty(@RequestBody Faculty faculty, @PathVariable long id) {

		Optional<Faculty> facultyOptional = facultyRepo.findById(id);

		if (!facultyOptional.isPresent())
			return ResponseEntity.notFound().build();

		faculty.setId(id);
		Faculty updateFaculty=facultyRepo.save(faculty);
		
		return ResponseEntity.ok().body(updateFaculty);
		//return "update success";
	}
}
