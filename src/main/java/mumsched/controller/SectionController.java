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

import mumsched.repo.SectionRepo;
import mumsched.model.*;
import java.lang.*;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class SectionController {
	
	@Autowired
	private SectionRepo sectionRepo;
	
	//=== Get All Sections ===
	@GetMapping("/section")
	public List<Section> getAllSections() {
		return sectionRepo.findAll();
	}
	
	//=== Get Section by ID  ===
	@GetMapping("/section/{id}")
	public Section getSectionById(@PathVariable long id){
		Optional<Section> section = sectionRepo.findById(id);

		if (!section.isPresent())  
			ResponseEntity.notFound().build();

		return section.get();
	}
	
	//=== Delete Section by ID ===
	@DeleteMapping("/section/{id}")
	public void deleteSection(@PathVariable long id) {
		sectionRepo.deleteById(id);
	}
	
	//=== Create new Section =======
	@PostMapping("/section")
	public Section createSection(@RequestBody Section section) {
		return sectionRepo.save(section);

	}
	
	//=== Update Section by ID=======
	@PutMapping("/section/{id}")
	public ResponseEntity<Object> updateSection(@RequestBody Section section, @PathVariable long id) {

		Optional<Section> sectionOptional = sectionRepo.findById(id);

		if (!sectionOptional.isPresent())
			return ResponseEntity.notFound().build();

		section.setId(id);
		Section updateSection=sectionRepo.save(section);
		
		return ResponseEntity.ok().body(updateSection);
	}
}
