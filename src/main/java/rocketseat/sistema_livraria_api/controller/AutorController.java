package rocketseat.sistema_livraria_api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocketseat.sistema_livraria_api.dto.AutorDTO;
import rocketseat.sistema_livraria_api.dto.ErrorMessage;
import rocketseat.sistema_livraria_api.exception.AutorConflictException;
import rocketseat.sistema_livraria_api.model.Autor;
import rocketseat.sistema_livraria_api.service.AutorService;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping("/")
    public ResponseEntity<?> createAutor(@RequestBody AutorDTO autorDTO) {
        try {
            Autor newAutor = autorService.addNewAutor(autorDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(newAutor);
        } catch (AutorConflictException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAutor(@PathVariable Integer id) {
        try {
            autorService.deleteAutor(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (AutorConflictException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(e.getMessage()));
        }
    }
}
