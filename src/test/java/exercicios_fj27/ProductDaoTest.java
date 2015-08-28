package exercicios_fj27;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.Filter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import br.com.casadocodigo.loja.builder.ProductBuilder;
import br.com.casadocodigo.loja.conf.ConfiguracaoWebApp;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.conf.SecurityConfiguration;
import br.com.casadocodigo.loja.dao.ProdutoDao;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { ConfiguracaoWebApp.class,
		JPAConfiguration.class, SecurityConfiguration.class
})
@ActiveProfiles("test")
public class ProductDaoTest {
	@Autowired
	private ProdutoDao productDAO;
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private Filter filterChainSpringSecurity;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilters(filterChainSpringSecurity).build();
	}
	
	@Test
	public void onlyAdminShoudAccessProductsForm() throws Exception {
	    RequestPostProcessor processor =
	            SecurityMockMvcRequestPostProcessors
	            .user("comprador@gmail.com").password("123456")
	            .roles("COMPRADOR");
	            
    			this.mockMvc.perform(get("/produtos/form").with(processor))
    			.andExpect(MockMvcResultMatchers.status().is(403));
	}

		@Test
		@Transactional
		public void shouldListAllBooksInTheHome() throws Exception {
			this.mockMvc.perform(get("/produtos")).andExpect(
					MockMvcResultMatchers
							.forwardedUrl("/WEB-INF/views/products/list.jsp"));
		}

	@Transactional
	@Test
	public void shouldSumAllPricesOfEachBookPerType() {

		List<Product> printedBooks = ProductBuilder
				.newProduct(BookType.PRINTED, BigDecimal.TEN).more(4)
				.buildAll();

		printedBooks.stream().forEach(productDAO::adiciona);

		List<Product> ebooks = ProductBuilder
				.newProduct(BookType.EBOOK, BigDecimal.TEN).more(4).buildAll();

		ebooks.stream().forEach(productDAO::adiciona);

		BigDecimal value = productDAO.sumPricesPerType(BookType.PRINTED);
		Assert.assertEquals(new BigDecimal(50).setScale(2), value);
	}
}