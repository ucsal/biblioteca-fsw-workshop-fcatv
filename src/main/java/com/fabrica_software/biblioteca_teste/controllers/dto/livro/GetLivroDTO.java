package com.fabrica_software.biblioteca_teste.controllers.dto.livro;

import com.fabrica_software.biblioteca_teste.domain.Livro;

public record GetLivroDTO(
		Long id,
		String isbn,
		String titulo,
		String autor,
		Integer quantidadeTotal) {
		public GetLivroDTO(Livro livro) {
			this(livro.getId(), livro.getIsbn(), livro.getTitulo(), livro.getAutor(), livro.getQuantidadeTotal());
		}
}
