package com.fabrica_software.biblioteca_teste.controllers.dto.emprestimo;

import java.time.LocalDateTime;

import com.fabrica_software.biblioteca_teste.domain.EstudanteLivro;


public record GetEmprestimoDTO(
		Long id,
		Long id_estudante,
		Long id_livro,
		LocalDateTime dataHoraInicioEmprestimo,
		LocalDateTime dataHoraFimEmprestimo) {

	public GetEmprestimoDTO (EstudanteLivro emprestimo) {
		this(emprestimo.getId(), emprestimo.getEstudante().getId(), emprestimo.getLivro().getId(),
				emprestimo.getDataHoraInicioEmprestimo(), emprestimo.getDataHoraFimEmprestimo());
	}
}
