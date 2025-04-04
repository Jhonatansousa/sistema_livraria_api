package rocketseat.sistema_livraria_api.repo;

import org.springframework.data.repository.CrudRepository;
import rocketseat.sistema_livraria_api.model.Emprestimo;

import java.time.LocalDateTime;
import java.util.List;

public interface EmprestimoRepo extends CrudRepository<Emprestimo, Integer> {
    List<Emprestimo> findByDataEmprestimoBetween(LocalDateTime dataEmprestimoAfter, LocalDateTime dataEmprestimoBefore);
}
