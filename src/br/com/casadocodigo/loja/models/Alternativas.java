package br.com.casadocodigo.loja.models;

import java.util.List;

import javax.validation.Valid;

public class Alternativas {
	
	@Valid 
	private List<Alternativa> alternativas;

	public List<Alternativa> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<Alternativa> alternativas) {
		this.alternativas = alternativas;
	}
	
	@Override
	public String toString() {
		return alternativas.toString();
	}

}
