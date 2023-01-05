package pi.quitter.quitter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pi.quitter.quitter.models.Pergunta;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Long>{

}