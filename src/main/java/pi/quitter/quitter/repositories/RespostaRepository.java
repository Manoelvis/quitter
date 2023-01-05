package pi.quitter.quitter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pi.quitter.quitter.models.Pergunta;
import pi.quitter.quitter.models.Resposta;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long>{

	List<Resposta> findByPergunta(Pergunta pergunta);
}