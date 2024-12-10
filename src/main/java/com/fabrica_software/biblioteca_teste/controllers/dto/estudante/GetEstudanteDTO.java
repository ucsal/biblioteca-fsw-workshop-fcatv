package com.fabrica_software.biblioteca_teste.controllers.dto.estudante;

import com.fabrica_software.biblioteca_teste.domain.Estudante;

public record GetEstudanteDTO(
		Long id,
		String matricula,
		String nome) {
		
	public GetEstudanteDTO(Estudante estudante) {
		this(estudante.getId(), estudante.getMatricula(), estudante.getNome());
	}
}
