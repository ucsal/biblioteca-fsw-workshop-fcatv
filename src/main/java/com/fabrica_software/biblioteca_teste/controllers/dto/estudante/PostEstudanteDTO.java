package com.fabrica_software.biblioteca_teste.controllers.dto.estudante;

import com.fabrica_software.biblioteca_teste.domain.Estudante;

import jakarta.validation.constraints.NotEmpty;

public record PostEstudanteDTO(
		@NotEmpty
		String matricula,
		@NotEmpty
		String nome) {

	public Estudante toModel() {
		Estudante estudante = new Estudante();
		estudante.setMatricula(this.matricula);
		estudante.setNome(this.nome);
		return estudante;
	}
}
