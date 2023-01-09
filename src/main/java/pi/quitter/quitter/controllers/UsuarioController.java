package pi.quitter.quitter.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pi.quitter.quitter.models.Role;
import pi.quitter.quitter.models.Usuario;
import pi.quitter.quitter.repositories.RoleRepository;
import pi.quitter.quitter.repositories.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository ur;

	@Autowired
	private RoleRepository rr;

	@GetMapping("/cadastro")
	public String form(Usuario usuario) {
		return "usuarios/form";
	}

	@PostMapping("/cadastro")
	public String salvar(@Valid Usuario usuario, BindingResult result) {

		if (result.hasErrors()) {
			return "redirect:/cadastro";
		}

		ArrayList<Role> roles = new ArrayList<Role>();

		Role role = rr.findByNome("ROLE_USUARIO");
		roles.add(role);

		usuario.setRoles(roles);

		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));

		ur.save(usuario);

		return "redirect:/login";
	}
}