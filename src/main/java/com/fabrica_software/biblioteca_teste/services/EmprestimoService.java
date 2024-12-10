package com.fabrica_software.biblioteca_teste.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabrica_software.biblioteca_teste.controllers.dto.emprestimo.EmprestimoDTO;
import com.fabrica_software.biblioteca_teste.domain.Estudante;
import com.fabrica_software.biblioteca_teste.domain.EstudanteLivro;
import com.fabrica_software.biblioteca_teste.domain.Livro;
import com.fabrica_software.biblioteca_teste.repositories.EstudanteLivroRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class EmprestimoService {

	@Autowired
	private EstudanteService estudanteService;
	@Autowired
	private LivroService livroService;
	@Autowired
	private EstudanteLivroRepository estudanteLivroRepository;
	
	@Transactional
	public EstudanteLivro realizarEmprestimo(EmprestimoDTO emprestimoDTO) {
		Estudante estudante = estudanteService.findById(emprestimoDTO.id_estudante());
		Livro livro = livroService.findById(emprestimoDTO.id_livro());
		EstudanteLivro emprestimo = new EstudanteLivro();
		
		emprestimo.setDataHoraInicioEmprestimo(LocalDateTime.now());
		emprestimo.setEstudante(estudante);
		emprestimo.setLivro(livro);
		
		estudanteLivroRepository.save(emprestimo);
		return emprestimo;
	}
	
	public EstudanteLivro findById(Long id) {
		EstudanteLivro emprestimo = estudanteLivroRepository.findById(id).orElseThrow(
				()-> new EntityNotFoundException("Emprestimo com ID " + id + " não encontrado"));
		return emprestimo;
	}

	@Transactional
	public EstudanteLivro realizarDevolucao(Long id) {
		EstudanteLivro emprestimo = estudanteLivroRepository.findById(id).orElseThrow(
				()-> new EntityNotFoundException("Emprestimo com ID " + id + " não encontrado"));
		emprestimo.setDataHoraFimEmprestimo(LocalDateTime.now());
		return emprestimo;
	}
}
