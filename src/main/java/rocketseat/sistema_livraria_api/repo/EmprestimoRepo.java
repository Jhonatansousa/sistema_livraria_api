package rocketseat.sistema_livraria_api.repo;

import org.springframework.data.repository.CrudRepository;
import rocketseat.sistema_livraria_api.model.Emprestimo;

public interface EmprestimoRepo extends CrudRepository<Emprestimo, Integer> {
}
