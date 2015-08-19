package br.com.casadocodigo.loja.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.ProdutoDao;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Product;
import br.com.casadocodigo.loja.validations.ProductValidator;

@Controller
@RequestMapping("/produtos")
public class ProductsController {

	@Autowired
	private ProdutoDao productDao;

	@Autowired
	private FileSaver fileSaver;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new ProductValidator());
	}

	@RequestMapping("/form")
	public ModelAndView form(@ModelAttribute Product product) {
		ModelAndView mv = new ModelAndView("products/form");
		mv.addObject("bookTypes", BookType.values());

		return mv;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {
		List<Product> produtos = productDao.list();

		System.out.println(produtos);

		ModelAndView mv = new ModelAndView("products/list");
		mv.addObject("products", produtos);

		return mv;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ModelAndView show(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("products/show");
		modelAndView.addObject("product", productDao.find(id));
		return modelAndView;
	}

	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(MultipartFile summary,
			@ModelAttribute("product") @Valid Product product,
			BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return form(product);
		}

		String webPath = fileSaver.write("", summary);
		product.setSummaryPath(webPath);

		productDao.adiciona(product);

		redirectAttributes.addFlashAttribute("success",
				"Produto cadastrado com sucesso");
		return new ModelAndView("redirect:produtos");
	}

}
