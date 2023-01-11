package pi.quitter.quitter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.
		authorizeRequests()
		.antMatchers("/cadastro").permitAll()
		.antMatchers("/user/**").hasRole("USUARIO")
		.antMatchers("/ava/**").hasRole("AVALIADOR")
		.antMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest()
		.authenticated()
	.and()
		.formLogin()
			.loginPage("/login")
			.permitAll()
		.and()
		.logout()
			.logoutSuccessUrl("/login?logout")
			.permitAll();
	}
}