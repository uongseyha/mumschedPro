package mumsched.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import mumsched.repo.UserRepo;
import mumsched.model.*;
import java.lang.*;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserRepo userRepo;
	
	//=== Get All Users ===
	@GetMapping("/user")
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	//=== Get User by ID  ===
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable long id){
		Optional<User> user = userRepo.findById(id);

		if (!user.isPresent())  
			ResponseEntity.notFound().build();

		return user.get();
	}
	
//	//=== Get User by User & Password  ===
//	@GetMapping("/user/check/{name}")
//	public User getUserByNamePassword(@PathVariable String userName){
//		User user=userRepo.findUserByNamePassword(userName); 
//		
//		return user;
//
//	}
	
	
	
	//=== Delete User by ID ===
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable long id) {
		userRepo.deleteById(id);
	}
	
	//=== Create new User =======
	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		return userRepo.save(user);

	}
	
	//=== Update User by ID=======
	@PutMapping("/user/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable long id) {

		Optional<User> userOptional = userRepo.findById(id);

		if (!userOptional.isPresent())
			return ResponseEntity.notFound().build();

		user.setId(id);
		User updateUser=userRepo.save(user);
		
		return ResponseEntity.ok().body(updateUser);
	}
}
