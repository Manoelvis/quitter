package pi.quitter.quitter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import pi.quitter.quitter.models.Usuario;
import pi.quitter.quitter.repositories.UsuarioRepository;

@Component
public class CustomDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByMatricula(username);

		if(usuario == null) {
			throw new UsernameNotFoundException("User not found");
		}

		return usuario;
	}
}