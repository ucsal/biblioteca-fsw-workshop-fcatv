package com.fabrica_software.biblioteca_teste.controllers;

import java.net.URI;

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

import com.fabrica_software.biblioteca_teste.controllers.dto.livro.GetLivroDTO;
import com.fabrica_software.biblioteca_teste.controllers.dto.livro.PostLivroDTO;
import com.fabrica_software.biblioteca_teste.controllers.dto.livro.PutLivroDTO;
import com.fabrica_software.biblioteca_teste.domain.Livro;
import com.fabrica_software.biblioteca_teste.services.LivroService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private LivroService livroService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> post(@RequestBody @Valid PostLivroDTO livroDTO, 
			UriComponentsBuilder uriComponentsBuilder ){
		Livro livroModel = livroDTO.toModel();
		Livro livroPersistido = livroService.save(livroModel);
		
		URI uri = uriComponentsBuilder.path("/livros/{id}").buildAndExpand(livroPersistido.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id){
	 	Livro livro = livroService.findById(id);
	 	GetLivroDTO getLivroDTO = new GetLivroDTO(livro);
	 	return ResponseEntity.ok(getLivroDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> put(@PathVariable Long id, @RequestBody @Valid PutLivroDTO putLivroDTO){
		Livro livro = livroService.update(id, putLivroDTO);
		GetLivroDTO getLivroDTO = new GetLivroDTO(livro);
		return ResponseEntity.ok(getLivroDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		livroService.delete(id);
		return ResponseEntity.noContent().build();
	}
}