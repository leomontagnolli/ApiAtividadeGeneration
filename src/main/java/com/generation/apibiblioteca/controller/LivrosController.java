package com.generation.apibiblioteca.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.util.UriComponentsBuilder;

import com.generation.apibiblioteca.model.Livro;
import com.generation.apibiblioteca.repository.LivrosRepository;

@RestController
@RequestMapping("/livros")
public class LivrosController {
	
	@Autowired
	private LivrosRepository repository;
	
	@GetMapping
	public List<Livro> listar(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Livro> detalhar (@PathVariable Long id) {
		Optional<Livro> livro = repository.findById(id);
		if(livro.isPresent()) {
			return ResponseEntity.ok(livro.get());
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping
	public ResponseEntity<Livro> cadastar (@RequestBody @Valid Livro livro, UriComponentsBuilder uriBuilder){
		repository.save(livro);
		URI uri = uriBuilder.path("/livros/{id}").buildAndExpand(livro.getId()).toUri();
		return ResponseEntity.created(uri).body(livro);
		
	}
	@PutMapping
	public ResponseEntity<Livro> atualizar (@RequestBody Livro livro) {
		return ResponseEntity.ok(repository.save(livro));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Livro> deletar ( @PathVariable Long id) {
		Optional<Livro> livro = repository.findById(id);
		if(livro.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.badRequest().build();
	}
}
