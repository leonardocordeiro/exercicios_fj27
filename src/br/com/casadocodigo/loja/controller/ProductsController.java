package br.com.casadocodigo.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDao;
import br.com.casadocodigo.loja.models.Product;

@Controller
public class ProductsController {
	
	@Autowired
	private ProdutoDao productDao;
	
	@RequestMapping("/produtos/form")
	public ModelAndView form() { 
		return new ModelAndView("products/form");
	}
	
	@Transactional
	@RequestMapping("/produtos")
	public String cadastra(Product product) { 
		System.out.println("Cadastrando o produto: " + product);
		productDao.adiciona(product);
		
		return "products/form";
	}

}
