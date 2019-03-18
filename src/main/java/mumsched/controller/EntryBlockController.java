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

import mumsched.repo.EntryBlockRepo;
import mumsched.model.*;
import java.lang.*;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class EntryBlockController {
	
	@Autowired
	private EntryBlockRepo entryBlockRepo;
	
	//=== Get All EntryBlocks ===
	@GetMapping("/entryblock")
	public List<EntryBlock> getAllEntryBlocks() {
		return entryBlockRepo.findAll();
	}
	
	//=== Get EntryBlock by ID  ===
	@GetMapping("/entryblock/{id}")
	public EntryBlock getEntryBlockById(@PathVariable long id){
		Optional<EntryBlock> entryBlock = entryBlockRepo.findById(id);

		if (!entryBlock.isPresent())  
			ResponseEntity.notFound().build();

		return entryBlock.get();
	}	
	
	//=== Delete EntryBlock by ID ===
	@DeleteMapping("/entryblock/{id}")
	public void deleteEntryBlock(@PathVariable long id) {
		entryBlockRepo.deleteById(id);
	}
	
	//=== Create new EntryBlock =======
	@PostMapping("/entryblock")
	public EntryBlock createEntryBlock(@RequestBody EntryBlock entryBlock) {
		return entryBlockRepo.save(entryBlock);

	}
	
	//=== Update EntryBlock by ID=======
	@PutMapping("/entryblock/{id}")
	public ResponseEntity<Object> updateEntryBlock(@RequestBody EntryBlock entryBlock, @PathVariable long id) {

		Optional<EntryBlock> entryBlockOptional = entryBlockRepo.findById(id);

		if (!entryBlockOptional.isPresent())
			return ResponseEntity.notFound().build();

		entryBlock.setId(id);
		EntryBlock updateEntryBlock=entryBlockRepo.save(entryBlock);
		
		return ResponseEntity.ok().body(updateEntryBlock);
	}
}
