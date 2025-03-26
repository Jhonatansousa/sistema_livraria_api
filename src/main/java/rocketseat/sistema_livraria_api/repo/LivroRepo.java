package rocketseat.sistema_livraria_api.repo;

import org.springframework.data.repository.CrudRepository;
import rocketseat.sistema_livraria_api.model.Livro;

import java.util.List;

public interface LivroRepo extends CrudRepository<Livro, Integer> {
    List<Livro> findByTitulo(String titulo);

    List<Livro> findByAutor(String autor);

    List<Livro> findByTituloAndAutor(String titulo, String autor);
}
