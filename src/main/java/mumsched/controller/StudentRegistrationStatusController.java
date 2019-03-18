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

import mumsched.repo.StudentRegistrationStatusRepo;
import mumsched.model.*;
import java.lang.*;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class StudentRegistrationStatusController {
	
	@Autowired
	private StudentRegistrationStatusRepo studentRegistrationStatusRepo;
	
	//=== Get All StudentRegistrationStatuss ===
	@GetMapping("/studentRegistrationStatus")
	public List<StudentRegistrationStatus> getAllStudentRegistrationStatuss() {
		return studentRegistrationStatusRepo.findAll();
	}
	
	//=== Get StudentRegistrationStatus by ID  ===
	@GetMapping("/studentRegistrationStatus/{id}")
	public StudentRegistrationStatus getStudentRegistrationStatusById(@PathVariable long id){
		Optional<StudentRegistrationStatus> studentRegistrationStatus = studentRegistrationStatusRepo.findById(id);

		if (!studentRegistrationStatus.isPresent())  
			ResponseEntity.notFound().build();

		return studentRegistrationStatus.get();
	}
	
	//=== Delete StudentRegistrationStatus by ID ===
	@DeleteMapping("/studentRegistrationStatus/{id}")
	public void deleteStudentRegistrationStatus(@PathVariable long id) {
		studentRegistrationStatusRepo.deleteById(id);
	}
	
	//=== Create new StudentRegistrationStatus =======
	@PostMapping("/studentRegistrationStatus")
	public StudentRegistrationStatus createStudentRegistrationStatus(@RequestBody StudentRegistrationStatus studentRegistrationStatus) {
		return studentRegistrationStatusRepo.save(studentRegistrationStatus);

	}
	
	//=== Update StudentRegistrationStatus by ID=======
	@PutMapping("/studentRegistrationStatus/{id}")
	public ResponseEntity<Object> updateStudentRegistrationStatus(@RequestBody StudentRegistrationStatus studentRegistrationStatus, @PathVariable long id) {

		Optional<StudentRegistrationStatus> studentRegistrationStatusOptional = studentRegistrationStatusRepo.findById(id);

		if (!studentRegistrationStatusOptional.isPresent())
			return ResponseEntity.notFound().build();

		studentRegistrationStatus.setId(id);
		StudentRegistrationStatus updateStudentRegistrationStatus=studentRegistrationStatusRepo.save(studentRegistrationStatus);
		
		return ResponseEntity.ok().body(updateStudentRegistrationStatus);
	}
}
