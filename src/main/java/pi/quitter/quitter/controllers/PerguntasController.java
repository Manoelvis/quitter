package pi.quitter.quitter.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pi.quitter.quitter.models.Pergunta;
import pi.quitter.quitter.repositories.PerguntaRepository;

@Controller
@RequestMapping("/perguntas")
public class PerguntasController {

	@Autowired
	private PerguntaRepository pr;

	@GetMapping("/form")
	public String form() {
		return "perguntas/formPergunta";
	}

	@PostMapping
	public String adicionarPergunta(Pergunta pergunta) {

		pr.save(pergunta);

		return "perguntas/pergunta-adicionada";
	}
	
	@GetMapping
	public ModelAndView listarPerguntas() {
		
		List<Pergunta> perguntas = pr.findAll();
		ModelAndView mv = new ModelAndView("perguntas/lista");
		mv.addObject("perguntas", perguntas);
		return mv;
	}
}