package com.fabrica_software.biblioteca_teste.controllers.dto.emprestimo;

import com.fabrica_software.biblioteca_teste.domain.EstudanteLivro;

import jakarta.validation.constraints.NotNull;

public record EmprestimoDTO(
		@NotNull
		Long id_estudante,
		@NotNull
		Long id_livro) {
}
