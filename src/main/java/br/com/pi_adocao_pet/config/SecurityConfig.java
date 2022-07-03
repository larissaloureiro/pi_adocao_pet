package br.com.pi_adocao_pet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.pi_adocao_pet.security.jwt.JwtConfigurer;
import br.com.pi_adocao_pet.security.jwt.JwtProvider;



@Configuration
public class SecurityConfig {
	
	@Autowired
	private JwtProvider jwtProvider;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = 
				new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return authenticationManagerBean();
	}
	
	protected void configure( HttpSecurity http) throws Exception {
		http
			.httpBasic().disable()
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.authorizeRequests()
				.antMatchers("/auth/signin", "/api-docs/**", "/swagger-ui.html").permitAll()
				.antMatchers("/api/**").authenticated()
				.antMatchers("/users").denyAll()
			.and()
			.apply(new JwtConfigurer(jwtProvider));
	}
	
}

