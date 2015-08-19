package br.com.casadocodigo.loja.validations;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Aluno;

public class AlunoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Aluno.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "field.required");
	}

}
