package pi.quitter.quitter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@Configuration
public class WebSecurityConfig extends WebSecurityConfiguration{


	protected void configure(HttpSecurity http) throws Exception{
		
			http.
					authorizeRequests()
						.anyRequest()
						.authenticated()
					.and()
					.formLogin()
						.loginPage("/login")
						.permitAll();
	}
}