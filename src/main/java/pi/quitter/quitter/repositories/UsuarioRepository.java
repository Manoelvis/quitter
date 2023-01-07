package pi.quitter.quitter.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pi.quitter.quitter.models.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

	Usuario findByMatricula(String matricula);

	Iterable<Usuario> findAllByNomeContaining(String filter);
}
