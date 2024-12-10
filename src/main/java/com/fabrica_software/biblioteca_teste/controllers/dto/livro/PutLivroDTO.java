package com.fabrica_software.biblioteca_teste.controllers.dto.livro;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PutLivroDTO(
		@NotEmpty
		String titulo,
		@NotEmpty
		String autor,
		@NotNull
		@Min(value = 1)
		Integer quantidadeTotal) {

}
