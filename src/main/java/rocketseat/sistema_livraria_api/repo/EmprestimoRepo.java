package rocketseat.sistema_livraria_api.repo;

import org.springframework.data.repository.CrudRepository;
import rocketseat.sistema_livraria_api.model.Emprestimo;

import java.time.LocalDateTime;

public interface EmprestimoRepo extends CrudRepository<Emprestimo, Integer> {
    Iterable<Emprestimo> findByDataEmprestimoBetween(LocalDateTime dataEmprestimoAfter, LocalDateTime dataEmprestimoBefore);
}
