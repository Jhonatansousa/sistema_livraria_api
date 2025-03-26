package rocketseat.sistema_livraria_api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocketseat.sistema_livraria_api.exception.AutorConflictException;
import rocketseat.sistema_livraria_api.model.Autor;
import rocketseat.sistema_livraria_api.repo.AutorRepo;

@Service
public class AutorService {

    @Autowired
    private AutorRepo autorRepo;

    public Autor addNewAutor(Autor autor) {
        Autor newAutor = autorRepo.findByNomeIgnoreCase(autor.getNome()).orElseThrow(() -> new AutorConflictException("Já existe um autor com esse nome"));
        autorRepo.save(newAutor);
        return newAutor;
    }

    public void deleteAutor(Integer id) {
        Autor autor = autorRepo.findById(id).orElseThrow(()-> new AutorConflictException("Autor não encontrado"));
        autorRepo.delete(autor);
    }
}
