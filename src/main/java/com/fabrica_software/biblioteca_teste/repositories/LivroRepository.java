package com.fabrica_software.biblioteca_teste.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabrica_software.biblioteca_teste.domain.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
