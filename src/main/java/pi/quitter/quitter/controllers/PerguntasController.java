package pi.quitter.quitter.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pi.quitter.quitter.models.Pergunta;
import pi.quitter.quitter.models.Resposta;
import pi.quitter.quitter.repositories.PerguntaRepository;
import pi.quitter.quitter.repositories.RespostaRepository;

@Controller
@RequestMapping("/perguntas")
public class PerguntasController {

	@Autowired
	private PerguntaRepository pr;
	@Autowired
	private RespostaRepository rr;

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

	@GetMapping("/{id}")
	public ModelAndView detalharPerguntas(@PathVariable Long id) {

		ModelAndView md = new ModelAndView();
		Optional<Pergunta> opt = pr.findById(id);

		if (opt.isEmpty()) {
			md.setViewName("redirect:/perguntas");
			return md;
		}

		md.setViewName("perguntas/detalhesPergunta");
		Pergunta pergunta = opt.get();

		md.addObject("pergunta", pergunta);

		return md;
	}
	
	@PostMapping("/{idPergunta}")
	public String adicionarResposta(@PathVariable Long idPergunta, Resposta resposta) {
		
		Optional<Pergunta> opt = pr.findById(idPergunta);
		
		if(opt.isEmpty()) {
			
			return "redirect:/perguntas";
		}
		
		Pergunta pergunta = opt.get();
		resposta.setPergunta(pergunta);
		
		rr.save(resposta);
		
		return "redirect:/perguntas/{idPergunta}";
	}
}