package pi.quitter.quitter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PerguntasController {

	@RequestMapping("/perguntas/form")
	public String form() {
		return "formPergunta";
	}
}