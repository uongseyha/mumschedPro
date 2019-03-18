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

import mumsched.repo.RoleRepo;
import mumsched.model.*;
import java.lang.*;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class RoleController {
	
	@Autowired
	private RoleRepo roleRepo;
	
	//=== Get All Roles ===
	@GetMapping("/role")
	public List<Role> getAllRoles() {
		return roleRepo.findAll();
	}
	
	//=== Get Role by ID  ===
	@GetMapping("/role/{id}")
	public Role getRoleById(@PathVariable long id){
		Optional<Role> role = roleRepo.findById(id);

		if (!role.isPresent())  
			ResponseEntity.notFound().build();

		return role.get();
	}
	
	//=== Delete Role by ID ===
	@DeleteMapping("/role/{id}")
	public void deleteRole(@PathVariable long id) {
		roleRepo.deleteById(id);
	}
	
	//=== Create new Role =======
	@PostMapping("/role")
	public Role createRole(@RequestBody Role role) {
		return roleRepo.save(role);

	}
	
	//=== Update Role by ID=======
	@PutMapping("/role/{id}")
	public ResponseEntity<Object> updateRole(@RequestBody Role role, @PathVariable long id) {

		Optional<Role> roleOptional = roleRepo.findById(id);

		if (!roleOptional.isPresent())
			return ResponseEntity.notFound().build();

		role.setId(id);
		Role updateRole=roleRepo.save(role);
		
		return ResponseEntity.ok().body(updateRole);
	}
}
