package com.fabrica_software.biblioteca_teste.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabrica_software.biblioteca_teste.controllers.dto.livro.GetLivroDTO;
import com.fabrica_software.biblioteca_teste.controllers.dto.livro.PutLivroDTO;
import com.fabrica_software.biblioteca_teste.domain.Livro;
import com.fabrica_software.biblioteca_teste.repositories.LivroRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class LivroService {
	@Autowired
	private LivroRepository livroRepository;
	
	@Transactional
	public Livro save(Livro livroModel) {
		return livroRepository.save(livroModel);
	}

	public Livro findById(Long id) {
		Livro livro = livroRepository.findById(id).orElseThrow(
				()-> new EntityNotFoundException("Livro com ID " + id + " não encontrado"));
		return livro;
	}

	@Transactional
	public Livro update(Long id, @Valid PutLivroDTO putLivroDTO) {
		Livro livro = livroRepository.findById(id).orElseThrow(
				()-> new EntityNotFoundException("Livro com ID " + id + " não encontrado"));
		livro.atualizar(putLivroDTO);
		return livro;
	}

	public void delete(Long id) {
		livroRepository.deleteById(id);
	}

}