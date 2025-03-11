package rocketseat.sistema_livraria_api.repo;

import org.springframework.data.repository.CrudRepository;
import rocketseat.sistema_livraria_api.model.Autor;

import java.util.Optional;

public interface AutorRepo extends CrudRepository<Autor, Integer> {

    Optional<Autor> findByNomeIgnoreCase(String nomeAutor);
}
