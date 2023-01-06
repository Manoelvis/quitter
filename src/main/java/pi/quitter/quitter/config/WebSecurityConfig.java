package pi.quitter.quitter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

	@SuppressWarnings("deprecation")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.
		authorizeRequests()
		.requestMatchers("/").permitAll()
		.requestMatchers("/user/**").hasRole("USUARIO")
		.requestMatchers("/ava/**").hasRole("AVALIADOR")
		.requestMatchers("/admin/**").hasRole("ADMIN")
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
		return null;
	}
}