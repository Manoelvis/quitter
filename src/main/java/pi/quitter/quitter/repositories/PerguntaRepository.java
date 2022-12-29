package pi.quitter.quitter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pi.quitter.quitter.models.Pergunta;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long>{

}