package rocketseat.sistema_livraria_api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rocketseat.sistema_livraria_api.dto.LivroDTO;
import rocketseat.sistema_livraria_api.model.Livro;
import rocketseat.sistema_livraria_api.service.LivroService;

import java.util.List;

@RestController
public class LivroController {

    @Autowired
    private LivroService service;



    @GetMapping("/livros")
    public List<Livro> getAllLivros() {
        return service.getAllLivros();
    }



    @PostMapping("/livros")
    public ResponseEntity<Livro> createLivro(@RequestBody LivroDTO livroDTO) {
        Livro livro = service.addNewLivro(livroDTO);
        return ResponseEntity.ok().body(livro);
    }

}
