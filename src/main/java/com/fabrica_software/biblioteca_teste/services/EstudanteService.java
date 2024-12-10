package com.fabrica_software.biblioteca_teste.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabrica_software.biblioteca_teste.controllers.dto.estudante.GetEstudanteDTO;
import com.fabrica_software.biblioteca_teste.controllers.dto.estudante.PutEstudanteDTO;
import com.fabrica_software.biblioteca_teste.domain.Estudante;
import com.fabrica_software.biblioteca_teste.repositories.EstudanteRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class EstudanteService {

	@Autowired
	private EstudanteRepository estudanteRepository;
	
	@Transactional
	public Estudante save(Estudante estudanteModel) {
		return estudanteRepository.save(estudanteModel);
	}

	public Estudante findById(Long id) {
		Estudante estudante = estudanteRepository.findById(id).orElseThrow(
				()-> new EntityNotFoundException("Estudante com ID " + id + " não encontrado"));
		return estudante;
	}

	@Transactional
	public Estudante update(Long id, @Valid PutEstudanteDTO putEstudanteDTO) {
		Estudante estudante = estudanteRepository.findById(id).orElseThrow(
				()-> new EntityNotFoundException("Estudante com ID " + id + " não encontrado"));
		estudante.atualizar(putEstudanteDTO);
		return estudante;
	}

	public void delete(Long id) {
		estudanteRepository.deleteById(id);
	}

}
