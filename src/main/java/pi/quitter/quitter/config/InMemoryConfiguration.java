package pi.quitter.quitter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class InMemoryConfiguration {

	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
			builder
					.inMemoryAuthentication()
					.withUser("joao").password(encoder.encode("123")).roles("ADMIN", "USER")
					.and()
					.withUser("jose").password(encoder.encode("123")).roles("USER", "AVALIADOR");
	}
}