package pi.quitter.quitter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pi.quitter.quitter.models.Usuario;
import pi.quitter.quitter.repositories.UsuarioRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UsuarioRepository ur;
	
	@GetMapping("/usuarios")
	public ModelAndView listaUsuarios() {
		
		Iterable<Usuario> usuarios = ur.findAll();

		
		ModelAndView mv = new ModelAndView("admin/usuarios/lista");
		mv.addObject("usuarios", usuarios);
		
		return mv;
	}
}