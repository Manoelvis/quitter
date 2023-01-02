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
	public String form(Pergunta pergunta) {
		return "perguntas/formPergunta";
	}

	@PostMapping
	public String adicionarPergunta(Pergunta pergunta) {

		pr.save(pergunta);

		return "redirect:/perguntas";
	}

	@GetMapping
	public ModelAndView listarPerguntas() {

		List<Pergunta> perguntas = pr.findAll();
		ModelAndView mv = new ModelAndView("perguntas/lista");
		mv.addObject("perguntas", perguntas);
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id, Resposta resposta) {

		ModelAndView md = new ModelAndView();
		Optional<Pergunta> opt = pr.findById(id);

		if (opt.isEmpty()) {
			md.setViewName("redirect:/perguntas");
			return md;
		}

		md.setViewName("perguntas/detalhes");
		Pergunta pergunta = opt.get();

		md.addObject("pergunta", pergunta);

		List<Resposta> repostas = rr.findByPergunta(pergunta);
		md.addObject("respostas", repostas);

		return md;
	}

	@PostMapping("/{idPergunta}")
	public String adicionarResposta(@PathVariable Long idPergunta, Resposta resposta) {

		Optional<Pergunta> opt = pr.findById(idPergunta);

		if (opt.isEmpty()) {

			return "redirect:/perguntas";
		}

		Pergunta pergunta = opt.get();
		resposta.setPergunta(pergunta);

		rr.save(resposta);

		return "redirect:/perguntas/{idPergunta}";
	}

	@GetMapping("/{id}/selecionar")
	public ModelAndView selecionarPergunta(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Pergunta> opt = pr.findById(id);
		if (opt.isEmpty()) {
			md.setViewName("redirect:/perguntas");
			return md;
		}

		Pergunta pergunta = opt.get();
		md.setViewName("perguntas/formPergunta");
		md.addObject("pergunta", pergunta);

		return md;
	}

	@GetMapping("/{idPergunta}/respostas/{idResposta}/selecionar")
	public ModelAndView selecionarResposta(@PathVariable Long idPergunta, @PathVariable Long idResposta) {
		ModelAndView md = new ModelAndView();

		Optional<Pergunta> optPergunta = pr.findById(idPergunta);
		Optional<Resposta> optResposta = rr.findById(idResposta);

		if (optPergunta.isEmpty() || optResposta.isEmpty()) {
			md.setViewName("redirect:/perguntas");
			return md;
		}

		Pergunta pergunta = optPergunta.get();
		Resposta resposta = optResposta.get();

		if (pergunta.getId() != resposta.getPergunta().getId()) {
			md.setViewName("redirect:/perguntas");
			return md;
		}

		md.setViewName("perguntas/detalhes");
		md.addObject("resposta", resposta);
		md.addObject("pergunta", pergunta);
		md.addObject("respostas", rr.findByPergunta(pergunta));

		return md;
	}

	@GetMapping("/{id}/remover")
	public String apagarPergunta(@PathVariable Long id) {

		Optional<Pergunta> opt = pr.findById(id);

		if (!opt.isEmpty()) {
			Pergunta pergunta = opt.get();
			List<Resposta> respostas = rr.findByPergunta(pergunta);
			rr.deleteAll(respostas);
			pr.delete(pergunta);
		}

		return "redirect:/perguntas";
	}

	@GetMapping("/{idPergunta}/respostas/{idResposta}/remover")
	public String apagarResposta(@PathVariable Long idPergunta, @PathVariable Long idResposta) {

		Optional<Resposta> opt = rr.findById(idResposta);

		if (!opt.isEmpty()) {
			Resposta resposta = opt.get();
			rr.delete(resposta);
		}

		return "redirect:/perguntas/{idPergunta}";
	}
}