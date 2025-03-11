package rocketseat.sistema_livraria_api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rocketseat.sistema_livraria_api.model.Autor;
import rocketseat.sistema_livraria_api.service.AutorService;

@RestController
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping("/autor")
    public Autor createAutor(@RequestBody Autor autor) {
        return autorService.addNewAutor(autor);
    }
}
