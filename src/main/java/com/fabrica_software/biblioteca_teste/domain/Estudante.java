package com.fabrica_software.biblioteca_teste.domain;

import java.util.ArrayList;
import java.util.List;

import com.fabrica_software.biblioteca_teste.controllers.dto.estudante.PutEstudanteDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "estudantes")
public class Estudante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String matricula;
	private String nome;
	@OneToMany(mappedBy = "estudante")
	private List<EstudanteLivro> emprestimos = new ArrayList<>();
	
	public void atualizar(@Valid PutEstudanteDTO putEstudanteDTO) {
		this.nome = putEstudanteDTO.nome();
		
	}
}