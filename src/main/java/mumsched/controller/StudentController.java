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

import mumsched.repo.StudentRepo;
import mumsched.model.*;
import java.lang.*;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class StudentController {
	
	@Autowired
	private StudentRepo studentRepo;
	
	//=== Get All Students ===
	@GetMapping("/student")
	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}
	
	//=== Get Student by ID  ===
	@GetMapping("/student/{id}")
	public Student getStudentById(@PathVariable long id){
		Optional<Student> student = studentRepo.findById(id);

		if (!student.isPresent())  
			ResponseEntity.notFound().build();

		return student.get();
	}
	
	//=== Delete Student by ID ===
	@DeleteMapping("/student/{id}")
	public void deleteStudent(@PathVariable long id) {
		studentRepo.deleteById(id);
	}
	
	//=== Create new Student =======
	@PostMapping("/student")
	public Student createStudent(@RequestBody Student student) {
		return studentRepo.save(student);
		
//		Student savedStudent = studentRepo.save(student);
//
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(savedStudent.getId()).toUri();
//
//		return ResponseEntity.created(location).build();

	}
	
	//=== Update Student by ID=======
	@PutMapping("/student/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {

		Optional<Student> studentOptional = studentRepo.findById(id);

		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();

		student.setId(id);
		Student updateStudent=studentRepo.save(student);
		
		return ResponseEntity.ok().body(updateStudent);
		//return "update success";
	}
}
