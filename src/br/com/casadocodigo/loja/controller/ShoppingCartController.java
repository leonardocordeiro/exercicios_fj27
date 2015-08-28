package br.com.casadocodigo.loja.controller;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDao;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.PaymentData;
import br.com.casadocodigo.loja.models.Product;
import br.com.casadocodigo.loja.models.ShoppingCart;
import br.com.casadocodigo.loja.models.ShoppingItem;
import br.com.casadocodigo.loja.models.User;

@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {

	@Autowired
	private ProdutoDao productDAO;
	@Autowired
	private ShoppingCart shoppingCart;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private MailSender mailer;

	@RequestMapping(value = "/checkout")
	public Callable<String> checkout(@AuthenticationPrincipal User user) {
		return () -> {
			BigDecimal total = shoppingCart.getTotal();
			String uriToPay = "http://book-payment.herokuapp.com/payment";
			try {
				String response = restTemplate.postForObject(uriToPay,
						new PaymentData(total), String.class);

				sendEmail(user);
				return "redirect:/products";
			} catch (HttpClientErrorException exception) {
				return "forward:/shopping";
			}
		};
	}

	private void sendEmail(User user) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom("leonardo.b.cordeiro@ig.com.br");
		email.setTo("leonardobcordeirodev@gmail.com");
		email.setSubject("Nova compra");
		email.setText("corpo do email");

		mailer.send(email);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView add(Integer productId, @RequestParam BookType bookType) {
		ShoppingItem item = createItem(productId, bookType);
		shoppingCart.add(item);
		return new ModelAndView("redirect:/produtos");
	}

	private ShoppingItem createItem(Integer productId, BookType bookType) {
		Product product = productDAO.find(productId);
		ShoppingItem item = new ShoppingItem(product, bookType);
		return item;
	}
}