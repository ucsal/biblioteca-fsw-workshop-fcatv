package com.fabrica_software.biblioteca_teste.controllers.util;

public class NotFoundErroResponse {

	private int status;
	private String erro;
	private String mensagem;

	public NotFoundErroResponse(int status, String erro, String mensagem) {
		this.status = status;
		this.erro = erro;
		this.mensagem = mensagem;
	}

	// Getters e setters
	public int getStatus() {
		return status;
	}

	public String getErro() {
		return erro;
	}

	public String getMensagem() {
		return mensagem;
	}

}
