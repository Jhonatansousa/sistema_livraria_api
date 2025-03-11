package rocketseat.sistema_livraria_api.repo;

import org.springframework.data.repository.CrudRepository;
import rocketseat.sistema_livraria_api.model.Livro;

public interface LivroRepo extends CrudRepository<Livro, Integer> {
}
