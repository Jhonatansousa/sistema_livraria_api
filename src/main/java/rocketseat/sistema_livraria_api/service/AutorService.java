package rocketseat.sistema_livraria_api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocketseat.sistema_livraria_api.dto.AutorDTO;
import rocketseat.sistema_livraria_api.exception.AutorConflictException;
import rocketseat.sistema_livraria_api.model.Autor;
import rocketseat.sistema_livraria_api.repo.AutorRepo;

import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepo autorRepo;

    public Autor addNewAutor(AutorDTO autorDTO) {

        Optional<Autor> autor = autorRepo.findByNomeIgnoreCase(autorDTO.nome());
        if (autor.isPresent()) {
            throw new AutorConflictException("Já existe um autor com esse nome cadastrado");
        }
        Autor newAutor = new Autor();
        newAutor.setNome(autorDTO.nome());
        autorRepo.save(newAutor);
        return newAutor;
    }

    public void deleteAutor(Integer id) {
        Autor autor = autorRepo.findById(id).orElseThrow(()-> new AutorConflictException("Autor não encontrado"));
        autorRepo.delete(autor);
    }
}