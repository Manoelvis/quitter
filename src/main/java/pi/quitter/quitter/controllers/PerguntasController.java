package pi.quitter.quitter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pi.quitter.quitter.models.Pergunta;

@Controller
public class PerguntasController {

	@RequestMapping("/perguntas/form")
	public String form() {
		return "formPergunta";
	}
	
	@PostMapping("/perguntas")
	public String adicionarPergunta(Pergunta pergunta) {
		return "evento-adicionado";
	}
}