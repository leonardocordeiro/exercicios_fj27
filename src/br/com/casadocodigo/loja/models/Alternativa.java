package br.com.casadocodigo.loja.models;

import org.hibernate.validator.constraints.NotBlank;

public class Alternativa {
	@NotBlank
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return descricao;
	}

}
