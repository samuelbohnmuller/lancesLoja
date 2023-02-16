package br.com.alura.mvc.springMVC;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{ //configuração de login

	@Autowired
	private DataSource dataSource;
	
	//autoriza endereços 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/home/**") //excessão que home/qualquer coisa vai poder continuar logado
				.permitAll()
			.anyRequest().authenticated() 
		.and()
		.formLogin(form -> form
            .loginPage("/login")
            .defaultSuccessUrl("/usuario/pedido", true) //depois de logar sempre irá para url pedido
            .permitAll() //todos acessam a página de login
        )
		.logout(logout -> logout.logoutUrl("/logout") //quando o usuário clicar no botão que faz requisição para /logout, o spring deslogará ele
			.logoutSuccessUrl("/home")); //quando deslogo, vou pra home
		//.csrf().disable(); //desabilito para não tomar forbiden na url /pedido/novo quando buscar um contexto(endereço antes de /)
	}
	
	//autenticação com jdbc, encriptando senha
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		UserDetails user = User.builder()
					.username("mario")
					.password(encoder.encode("mario"))
					.roles("ADM")
					.build();
		
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(encoder).withUser(user);
	}
	
}
