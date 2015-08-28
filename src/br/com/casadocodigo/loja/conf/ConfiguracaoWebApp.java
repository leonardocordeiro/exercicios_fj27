package br.com.casadocodigo.loja.conf;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.models.Alternativa;
import br.com.casadocodigo.loja.viewresolver.JsonViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "br.com.casadocodigo.loja")
public class ConfiguracaoWebApp extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LocaleChangeInterceptor());
	}
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new Converter<String, Alternativa>() {

			@Override
			public Alternativa convert(String arg0) {
				System.out.println("Convertendo");
				Alternativa a = new Alternativa();
				a.setDescricao(arg0);
				
				return a;
			} 
			
		});
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public LocaleResolver localeResolver() {
		return new CookieLocaleResolver();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ViewResolver contentNegotiatingViewResolver(
			ContentNegotiationManager manager) {
		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
		resolvers.add(internalResourceViewResolver());
		resolvers.add(new JsonViewResolver());

		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setViewResolvers(resolvers);
		resolver.setContentNegotiationManager(manager);

		return resolver;
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
	public MailSender mailSender() {
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost("smtp.ig.com.br");
		javaMailSenderImpl.setPassword("leonardo941231cordeiro");
		javaMailSenderImpl.setPort(587);
		javaMailSenderImpl.setUsername("leonardo.b.cordeiro@ig.com.br");

		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", true);
		mailProperties.put("mail.smtp.starttls.enable", true);
		javaMailSenderImpl.setJavaMailProperties(mailProperties);
		
		return javaMailSenderImpl;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	// @Bean
	// public FormattingConversionService mvcConversionService() {
	// DefaultFormattingConversionService conversionService = new
	// DefaultFormattingConversionService(true);
	//
	// DateFormatterRegistrar registrar = new DateFormatterRegistrar();
	// registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
	//
	// registrar.registerFormatters(conversionService);
	//
	// return conversionService;
	// }

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {

		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);

		return resolver;
	}

}
