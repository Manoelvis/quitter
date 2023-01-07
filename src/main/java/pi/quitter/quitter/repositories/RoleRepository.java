package pi.quitter.quitter.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;

import pi.quitter.quitter.models.Role;

@Controller
public interface RoleRepository extends CrudRepository<Role, Long>{

}