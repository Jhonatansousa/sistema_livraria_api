package rocketseat.sistema_livraria_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocketseat.sistema_livraria_api.dto.EmprestimoNotaResponseDTO;
import rocketseat.sistema_livraria_api.dto.EmprestimoRequestDTO;
import rocketseat.sistema_livraria_api.dto.ErrorMessage;
import rocketseat.sistema_livraria_api.exception.EmprestimoException;
import rocketseat.sistema_livraria_api.model.Emprestimo;
import rocketseat.sistema_livraria_api.repo.EmprestimoRepo;
import rocketseat.sistema_livraria_api.service.EmprestimoService;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {


    @Autowired
    private EmprestimoService service;

    @PostMapping("/")
    public ResponseEntity<?> createEmprestimo(@RequestBody EmprestimoRequestDTO requestDTO) {
        try {
            Emprestimo emprestimo = service.createEmprestimo(requestDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new EmprestimoNotaResponseDTO(
                            emprestimo.getId(),
                            emprestimo.getLivro().getTitulo(),
                            emprestimo.getLivro().getAutor().getNome(),
                            emprestimo.getLivro().getGenero().toString(),
                            emprestimo.getCliente().getId(),
                            emprestimo.getDataEmprestimo(),
                            emprestimo.getDataDevolucao()
                    ));
        } catch (EmprestimoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(e.getMessage()));
        }
    }


    @GetMapping("/")
    public ResponseEntity<?> getAllEmprestimos() {
        //teste retornando um Iterable, posso tbm usar o JpaRepository ao inves do CrudRepository
        // isso pq o metodo findAll() do Jpa retorna um List<T> ao invés do Iterator do CrudRepository
        //caso ainda estivesse usando List<T>, iria ter que usar um cast "(List<Emprestimo>)" pra deixar explícito que trata-se de uma lista
        Iterable<Emprestimo> emprestimos = service.getAllEmprestimos();

        return ResponseEntity.status(HttpStatus.OK).body(emprestimos);
    }


    @PutMapping("/{id}/devolucao")
    public ResponseEntity<?> devolucao(@PathVariable Integer id) {
        try {
            Emprestimo emprestimo = service.updateEmprestimo(id);
            return ResponseEntity.status(HttpStatus.OK).body(emprestimo);
        } catch (EmprestimoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(e.getMessage()));
        }
    }


    @GetMapping("/search-date")
    public ResponseEntity<?> getEmprestimoByDateRange(
            @RequestParam String inicio,
            @RequestParam String fim
    ) {
        try {
            LocalDateTime dataInicio = LocalDateTime.parse(inicio);
            LocalDateTime dataFim = LocalDateTime.parse(fim);
            Iterable<Emprestimo> emprestimos = service.getEmprestimoByDateRange(dataInicio, dataFim);
            return ResponseEntity.status(HttpStatus.OK).body(emprestimos);
        } catch(DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(e.getMessage()));
        }
    }

}
