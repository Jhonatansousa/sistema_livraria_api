package rocketseat.sistema_livraria_api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocketseat.sistema_livraria_api.dto.ErrorMessage;
import rocketseat.sistema_livraria_api.dto.LivroDTO;
import rocketseat.sistema_livraria_api.exception.LivroNaoEncontradoException;
import rocketseat.sistema_livraria_api.model.Livro;
import rocketseat.sistema_livraria_api.service.LivroService;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;


    @PostMapping("/")
    public ResponseEntity<?> createLivro(@RequestBody LivroDTO livroDTO) {
        try {
            Livro livro = livroService.addNewLivro(livroDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(livro);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(e.getMessage()));
        }
    }


//definir dessa forma o path, onde o titulo e autor são variáveis
    @GetMapping("/")
    public ResponseEntity<?> findLivro(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) boolean disponivel
    ) {
        //busca por todos ou titulos e/ou autor
        try {
            List<Livro> livros;
            if (titulo == null && autor == null && !disponivel) {
                livros = livroService.getAllLivros();
            } else if (disponivel) {
                livros = livroService.getLivrosDisponiveis();
            } else {
                livros = livroService.getLivrosByTituloOrAutor(titulo, autor);
            }
            return ResponseEntity.status(HttpStatus.OK).body(livros);
        } catch (LivroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(e.getMessage()));
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteLivro(
            @RequestParam Integer id
    ) {
        try {
            livroService.deleteByID(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (LivroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(e.getMessage()));
        }
    }
}
