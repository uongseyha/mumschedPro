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

import mumsched.repo.StudentRegistrationRepo;
import mumsched.model.*;
import java.lang.*;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class StudentRegistrationController {
	
	@Autowired
	private StudentRegistrationRepo studentRegistrationRepo;
	
	//=== Get All StudentRegistrations ===
	@GetMapping("/studentRegistration")
	public List<StudentRegistration> getAllStudentRegistrations() {
		return studentRegistrationRepo.findAll();
	}
	
	//=== Get StudentRegistration by ID  ===
	@GetMapping("/studentRegistration/{id}")
	public StudentRegistration getStudentRegistrationById(@PathVariable long id){
		Optional<StudentRegistration> studentRegistration = studentRegistrationRepo.findById(id);

		if (!studentRegistration.isPresent())  
			ResponseEntity.notFound().build();

		return studentRegistration.get();
	}
	
	//=== Delete StudentRegistration by ID ===
	@DeleteMapping("/studentRegistration/{id}")
	public void deleteStudentRegistration(@PathVariable long id) {
		studentRegistrationRepo.deleteById(id);
	}
	
	//=== Create new StudentRegistration =======
	@PostMapping("/studentRegistration")
	public StudentRegistration createStudentRegistration(@RequestBody StudentRegistration studentRegistration) {
		return studentRegistrationRepo.save(studentRegistration);

	}
	
	//=== Update StudentRegistration by ID=======
	@PutMapping("/studentRegistration/{id}")
	public ResponseEntity<Object> updateStudentRegistration(@RequestBody StudentRegistration studentRegistration, @PathVariable long id) {

		Optional<StudentRegistration> studentRegistrationOptional = studentRegistrationRepo.findById(id);

		if (!studentRegistrationOptional.isPresent())
			return ResponseEntity.notFound().build();

		studentRegistration.setId(id);
		StudentRegistration updateStudentRegistration=studentRegistrationRepo.save(studentRegistration);
		
		return ResponseEntity.ok().body(updateStudentRegistration);
	}
}
