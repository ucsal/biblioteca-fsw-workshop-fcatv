package com.fabrica_software.biblioteca_teste.controllers.util;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> tratarErro400(MethodArgumentNotValidException exception) {
		var erros = exception.getFieldErrors();
		return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> tratarErroRecursoNaoEncontrado(EntityNotFoundException exception){
		String mensagemErro = exception.getMessage();
		NotFoundErroResponse notFoundErroResponse = new NotFoundErroResponse(
	            HttpStatus.NOT_FOUND.value(),
	            "Recurso não encontrado",
	            mensagemErro
	        );
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundErroResponse);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> tratarErroJdbcSQLIntegrityConstraintViolationException(DataIntegrityViolationException exception){
	    String mensagem = "Erro de integridade no banco de dados. Verifique as restrições de dados.";
	    String campo = "campo desconhecido"; // valor padrão
	    if (exception.getCause() != null && exception.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
	        org.hibernate.exception.ConstraintViolationException constraintViolationException = (org.hibernate.exception.ConstraintViolationException) exception.getCause();
	        String constraintName = constraintViolationException.getConstraintName();
	        if (constraintName != null) {
	            String[] partes = constraintName.split("_");

	            if (partes.length > 1) {
	                campo = partes[1]; // Assume que o nome do campo esteja logo após "UNIQUE"
	            } else {
	                // Se não conseguir identificar, usa "campo desconhecido"
	                campo = "campo desconhecido";
	            }
	        }
	        mensagem = "Violação de restrição de integridade no campo: " + campo;
	    }
	    DadosErroValidacao erroValidacao = new DadosErroValidacao(campo.toLowerCase(), mensagem);
	    return ResponseEntity.status(HttpStatus.CONFLICT).body(erroValidacao);
	}
}
