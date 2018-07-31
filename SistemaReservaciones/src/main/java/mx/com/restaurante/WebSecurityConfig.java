package mx.com.restaurante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/","/home").permitAll()
		.antMatchers("/reservacion").permitAll()
		.antMatchers("/reservacion/confirmacion*").permitAll()
		.antMatchers("/gestionReservaciones*").permitAll()
		.antMatchers("/css/**").permitAll()
		
		//PErmitir cualquier peticion de images y style
		.and()
		.formLogin().loginPage("/login").permitAll()
		 .defaultSuccessUrl("/gestionReservaciones")
		 .and()
		.logout().permitAll();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("Christian").password("123").roles("USER");
		
	}
	
}
