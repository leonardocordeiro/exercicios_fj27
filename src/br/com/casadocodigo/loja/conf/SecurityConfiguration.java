package br.com.casadocodigo.loja.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.AntPathRequestMatcher;

import br.com.casadocodigo.loja.dao.UserDao;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDao dao;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(dao).passwordEncoder(
				new BCryptPasswordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception { 
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/produtos/form").hasRole("ADMIN")
				.antMatchers("/shopping/**").permitAll()
				.antMatchers(HttpMethod.POST, "/produtos").hasRole("ADMIN")
				.antMatchers("/produtos/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.rememberMe().disable()
			.formLogin().loginPage("/login")
				.defaultSuccessUrl("/produtos").permitAll()
				.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login").permitAll();
		

	}
}
