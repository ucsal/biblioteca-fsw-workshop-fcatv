package com.fabrica_software.biblioteca_teste.domain;

import java.util.ArrayList;
import java.util.List;

import com.fabrica_software.biblioteca_teste.controllers.dto.livro.PutLivroDTO;

import jakarta.persistence.Column;
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

@Entity(name = "livros")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	@Column(unique = true)
	private String isbn;
	private String titulo;
	private String autor;
	@OneToMany(mappedBy = "livro")
	private List<EstudanteLivro> emprestimos = new ArrayList<>();
	private Integer quantidadeTotal;

	public void atualizar(@Valid PutLivroDTO putLivroDTO) {
		this.autor = putLivroDTO.autor();
		this.titulo = putLivroDTO.titulo();
		this.quantidadeTotal = putLivroDTO.quantidadeTotal();

	}

}