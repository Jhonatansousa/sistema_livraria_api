package rocketseat.sistema_livraria_api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocketseat.sistema_livraria_api.model.Autor;
import rocketseat.sistema_livraria_api.repo.AutorRepo;

@Service
public class AutorService {

    @Autowired
    private AutorRepo autorRepo;

    public Autor addNewAutor(Autor autor) {
        return this.autorRepo.save(autor);
    }
}
