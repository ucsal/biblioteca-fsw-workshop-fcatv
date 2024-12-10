package com.fabrica_software.biblioteca_teste.controllers.dto.estudante;


import jakarta.validation.constraints.NotEmpty;

public record PutEstudanteDTO(
		@NotEmpty
		String nome) {

}
