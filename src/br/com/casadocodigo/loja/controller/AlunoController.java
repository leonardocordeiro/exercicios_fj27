package br.com.casadocodigo.loja.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.casadocodigo.loja.models.Aluno;
import br.com.casadocodigo.loja.validations.AlunoValidator;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

	@InitBinder
	public void init(WebDataBinder binder) { 
		binder.setValidator(new AlunoValidator());
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String form(Aluno aluno) { 
		return "aluno/form";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String save(@Valid Aluno aluno, BindingResult result) {
		
		for (ObjectError error : result.getAllErrors()) {
			System.out.printf("Code: %s Message: %s", error.getCode(), error.getDefaultMessage());
			
		}
		
		if(result.hasErrors())
			return form(aluno);

		System.out.println("cadastrou " + aluno.getNome());
		return form(aluno);
	}
}
