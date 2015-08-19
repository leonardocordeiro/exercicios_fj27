package br.com.casadocodigo.loja.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import sun.reflect.generics.visitor.Reifier;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "br.com.casadocodigo.loja")
public class ConfiguracaoWebApp {
	
	@Bean
	public RestTemplate restTemplate() { 
		return new RestTemplate();
	}
	

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("/WEB-INF/messages");
		bundle.setDefaultEncoding("ISO-8859-1");
		bundle.setCacheSeconds(1);
		return bundle;
	}
	
	@Bean
	public MultipartResolver multipartResolver() { 
		return new StandardServletMultipartResolver();
	}

//	@Bean
//	public FormattingConversionService mvcConversionService() {
//		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(true);
//
//		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
//		registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
//		
//		registrar.registerFormatters(conversionService);
//		
//		return conversionService;
//	}

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {

		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);

		return resolver;
	}

}
