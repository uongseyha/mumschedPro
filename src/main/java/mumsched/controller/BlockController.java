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

import mumsched.repo.BlockRepo;
import mumsched.model.*;
import java.lang.*;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class BlockController {
	
	@Autowired
	private BlockRepo blockRepo;
	
	//=== Get All Blocks ===
	@GetMapping("/block")
	public List<Block> getAllBlocks() {
		return blockRepo.findAll();
	}
	
	//=== Get Block by ID  ===
	@GetMapping("/block/{id}")
	public Block getBlockById(@PathVariable long id){
		Optional<Block> block = blockRepo.findById(id);

		if (!block.isPresent())  
			ResponseEntity.notFound().build();

		return block.get();
	}
	
	//=== Delete Block by ID ===
	@DeleteMapping("/block/{id}")
	public void deleteBlock(@PathVariable long id) {
		blockRepo.deleteById(id);
	}
	
	//=== Create new Block =======
	@PostMapping("/block")
	public Block createBlock(@RequestBody Block block) {
		return blockRepo.save(block);

	}
	
	//=== Update Block by ID=======
	@PutMapping("/block/{id}")
	public ResponseEntity<Object> updateBlock(@RequestBody Block block, @PathVariable long id) {

		Optional<Block> blockOptional = blockRepo.findById(id);

		if (!blockOptional.isPresent())
			return ResponseEntity.notFound().build();

		block.setId(id);
		Block updateBlock=blockRepo.save(block);
		
		return ResponseEntity.ok().body(updateBlock);
	}
}
