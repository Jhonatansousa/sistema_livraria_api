package rocketseat.sistema_livraria_api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import rocketseat.sistema_livraria_api.dto.LivroDTO;
import rocketseat.sistema_livraria_api.exception.LivroConflictException;
import rocketseat.sistema_livraria_api.exception.LivroNaoEncontradoException;
import rocketseat.sistema_livraria_api.model.Autor;
import rocketseat.sistema_livraria_api.model.Livro;
import rocketseat.sistema_livraria_api.repo.AutorRepo;
import rocketseat.sistema_livraria_api.repo.LivroRepo;

import java.util.List;


@Service
public class LivroService {


    @Autowired
    private LivroRepo livroRepo;

    @Autowired
    private AutorRepo autorRepo;


    public Livro addNewLivro(LivroDTO livroDTO) {
        Autor autor = autorRepo.findByNomeIgnoreCase(livroDTO.autorNome())
                .orElseGet(()-> {
                    Autor novoAutor = new Autor();
                    novoAutor.setNome(livroDTO.autorNome());
                    return autorRepo.save(novoAutor);
                });
        //validar se existe exatamente o mesmo titulo E autor
        Livro livro = new Livro();
        livro.setAutor(autor);
        livro.setTitulo(livroDTO.titulo());
        livro.setGenero(livroDTO.genero());
        return livroRepo.save(livro);
    }


    public List<Livro> getAllLivros() {
        return (List<Livro>) livroRepo.findAll();
    }


    public List<Livro> getLivrosByTituloOrAutor(String titulo, String autor) {
        List<Livro> livros;
        if (titulo == null) {
            livros = livroRepo.findByAutor_Nome(autor);
            return livros;
        } else if (autor == null) {
            livros = livroRepo.findByTitulo(titulo);
        } else {
            livros = livroRepo.findByTituloAndAutor_Nome(titulo, autor);
        }
        if (livros.isEmpty()) {
            throw new LivroNaoEncontradoException("Livro nao encontrado");
        }
        return livros;
    }


    public void deleteByID(Integer id) {
        Livro livro = livroRepo.findById(id).orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado"));
        livroRepo.delete(livro);
    }
}
