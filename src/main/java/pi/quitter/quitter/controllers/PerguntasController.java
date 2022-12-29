package pi.quitter.quitter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pi.quitter.quitter.models.Pergunta;
import pi.quitter.quitter.repositories.PerguntaRepository;

@Controller
public class PerguntasController {

	@Autowired
	private PerguntaRepository pr;

	@RequestMapping("/perguntas/form")
	public String form() {
		return "formPergunta";
	}

	@PostMapping("/perguntas")
	public String adicionarPergunta(Pergunta pergunta) {

		pr.save(pergunta);

		return "pergunta-adicionada";
	}
}