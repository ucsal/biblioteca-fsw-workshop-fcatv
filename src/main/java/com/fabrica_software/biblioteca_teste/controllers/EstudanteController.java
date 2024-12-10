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

import com.fabrica_software.biblioteca_teste.controllers.dto.estudante.GetEstudanteDTO;
import com.fabrica_software.biblioteca_teste.controllers.dto.estudante.PostEstudanteDTO;
import com.fabrica_software.biblioteca_teste.controllers.dto.estudante.PutEstudanteDTO;
import com.fabrica_software.biblioteca_teste.domain.Estudante;
import com.fabrica_software.biblioteca_teste.services.EstudanteService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/estudantes")
public class EstudanteController {

	@Autowired
	private EstudanteService estudanteService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> post(@RequestBody @Valid PostEstudanteDTO estudanteDTO, 
			UriComponentsBuilder uriComponentsBuilder ){
		Estudante estudanteModel = estudanteDTO.toModel();
		Estudante estudantePersistido = estudanteService.save(estudanteModel);
		
		URI uri = uriComponentsBuilder.path("/estudantes/{id}").buildAndExpand(estudantePersistido.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id){
	 	Estudante estudante = estudanteService.findById(id);
	 	GetEstudanteDTO getEstudanteDTO = new GetEstudanteDTO(estudante);
	 	return ResponseEntity.ok(getEstudanteDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> put(@PathVariable Long id, @RequestBody @Valid PutEstudanteDTO putEstudanteDTO){
		Estudante estudante = estudanteService.update(id, putEstudanteDTO);
		GetEstudanteDTO getEstudanteDTO = new GetEstudanteDTO(estudante);
		return ResponseEntity.ok(getEstudanteDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		estudanteService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
