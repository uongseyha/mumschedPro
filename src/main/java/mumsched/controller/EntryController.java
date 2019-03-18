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

import mumsched.repo.EntryRepo;
import mumsched.model.*;
import java.lang.*;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class EntryController {
	
	@Autowired
	private EntryRepo entryRepo;
	
	//=== Get All Entrys ===
	@GetMapping("/entry")
	public List<Entry> getAllEntrys() {
		return entryRepo.findAll();
	}
	
	//=== Get Entry by ID  ===
	@GetMapping("/entry/{id}")
	public Entry getEntryById(@PathVariable long id){
		Optional<Entry> entry = entryRepo.findById(id);

		if (!entry.isPresent())  
			ResponseEntity.notFound().build();

		return entry.get();
	}
	
	//=== Delete Entry by ID ===
	@DeleteMapping("/entry/{id}")
	public void deleteEntry(@PathVariable long id) {
		entryRepo.deleteById(id);
	}
	
	//=== Create new Entry =======
	@PostMapping("/entry")
	public Entry createEntry(@RequestBody Entry entry) {
		return entryRepo.save(entry);

	}
	
	//=== Update Entry by ID=======
	@PutMapping("/entry/{id}")
	public ResponseEntity<Object> updateEntry(@RequestBody Entry entry, @PathVariable long id) {

		Optional<Entry> entryOptional = entryRepo.findById(id);

		if (!entryOptional.isPresent())
			return ResponseEntity.notFound().build();

		entry.setId(id);
		Entry updateEntry=entryRepo.save(entry);
		
		return ResponseEntity.ok().body(updateEntry);
		//return "update success";
	}
}
