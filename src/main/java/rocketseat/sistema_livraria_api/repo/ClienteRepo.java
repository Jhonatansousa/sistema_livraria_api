package rocketseat.sistema_livraria_api.repo;

import org.springframework.data.repository.CrudRepository;
import rocketseat.sistema_livraria_api.model.Cliente;

public interface ClienteRepo extends CrudRepository<Cliente, Integer> {
}
