package rocketseat.sistema_livraria_api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import rocketseat.sistema_livraria_api.dto.LivroDTO;
import rocketseat.sistema_livraria_api.model.Autor;
import rocketseat.sistema_livraria_api.model.Livro;
import rocketseat.sistema_livraria_api.repo.AutorRepo;
import rocketseat.sistema_livraria_api.repo.LivroRepo;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {


    @Autowired
    private LivroRepo livroRepo;

    @Autowired
    private AutorRepo autorRepo;
    @Autowired
    private ResourceUrlProvider resourceUrlProvider;


    public List<Livro> getAllLivros() {
        return (List<Livro>) livroRepo.findAll();
    }

    //deletar esse metodo abaixo
//    public Livro addNewLivro(Livro livro) {
//        return this.livroRepo.save(livro);
//    }


    public Livro addNewLivro(LivroDTO livroDTO) {
        Autor autor = autorRepo.findByNomeIgnoreCase(livroDTO.autorNome())
                .orElseGet(()-> {
                    Autor novoAutor = new Autor();
                    novoAutor.setNome(livroDTO.autorNome());
                    return autorRepo.save(novoAutor);
                });
        Livro livro = new Livro();
        livro.setAutor(autor);
        livro.setTitulo(livroDTO.titulo());
        livro.setGenero(livroDTO.genero());
        return livroRepo.save(livro);
    }



    //criar metodo pra busca...


}
