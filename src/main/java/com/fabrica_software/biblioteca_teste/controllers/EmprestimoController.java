package com.fabrica_software.biblioteca_teste.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fabrica_software.biblioteca_teste.controllers.dto.emprestimo.EmprestimoDTO;
import com.fabrica_software.biblioteca_teste.controllers.dto.emprestimo.GetEmprestimoDTO;
import com.fabrica_software.biblioteca_teste.domain.EstudanteLivro;
import com.fabrica_software.biblioteca_teste.services.EmprestimoService;

@RequestMapping("emprestimos")
@RestController
public class EmprestimoController {

	@Autowired
	private EmprestimoService emprestimoService;
	
	@PostMapping
	public ResponseEntity<?> realizarEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO, 
			UriComponentsBuilder uriComponentsBuilder) {
		
		
		EstudanteLivro emprestimo = emprestimoService.realizarEmprestimo(emprestimoDTO);
		URI uri = uriComponentsBuilder.path("/emprestimos/{id}").buildAndExpand(emprestimo.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmprestimo(@PathVariable Long id){
		EstudanteLivro emprestimo = emprestimoService.findById(id);
		GetEmprestimoDTO getEmprestimoDTO = new GetEmprestimoDTO(emprestimo);
		return ResponseEntity.ok(getEmprestimoDTO);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<?> realizarDevolucao(@PathVariable Long id){
		EstudanteLivro emprestimo =  emprestimoService.realizarDevolucao(id);
		GetEmprestimoDTO getEmprestimoDTO = new GetEmprestimoDTO(emprestimo);
		return ResponseEntity.ok(getEmprestimoDTO); 
	}
}

