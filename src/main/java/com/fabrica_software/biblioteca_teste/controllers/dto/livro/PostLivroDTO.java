package com.fabrica_software.biblioteca_teste.controllers.dto.livro;

import com.fabrica_software.biblioteca_teste.domain.Livro;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PostLivroDTO(
		@NotEmpty
		String isbn,
		@NotEmpty
		String titulo,
		@NotEmpty
		String autor,
		@NotNull
		@Min(value = 1)
		Integer quantidadeTotal) {
	
	public Livro toModel() {
		Livro livro	= new Livro();
		livro.setIsbn(this.isbn);
		livro.setTitulo(this.titulo);
		livro.setAutor(this.autor);
		livro.setQuantidadeTotal(this.quantidadeTotal);
		return livro;
	}
}
