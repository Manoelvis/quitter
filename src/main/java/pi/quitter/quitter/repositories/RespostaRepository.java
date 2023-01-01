package pi.quitter.quitter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pi.quitter.quitter.models.Pergunta;
import pi.quitter.quitter.models.Resposta;

public interface RespostaRepository extends JpaRepository<Resposta, Long>{

	List<Resposta> findByPergunta(Pergunta pergunta);
}