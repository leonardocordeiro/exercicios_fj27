package br.com.casadocodigo.loja.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.casadocodigo.loja.models.Alternativas;

@Controller
public class AlternativaController {
	
	@RequestMapping(value="/alternativa", method=RequestMethod.POST)
	public String cadastrar(@Valid Alternativas alternativas, BindingResult result) {
		if(result.hasErrors()) 
			return "form";
		System.out.println(alternativas);
		return "form";
	}
	
	@RequestMapping(value="/alternativa", method=RequestMethod.GET)
	public String form(@Valid Alternativas alternativas) { 
		return "form";
	}
}
