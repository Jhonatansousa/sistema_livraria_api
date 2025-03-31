package rocketseat.sistema_livraria_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rocketseat.sistema_livraria_api.dto.EmprestimoNotaResponseDTO;
import rocketseat.sistema_livraria_api.dto.EmprestimoRequestDTO;
import rocketseat.sistema_livraria_api.dto.ErrorMessage;
import rocketseat.sistema_livraria_api.exception.EmprestimoException;
import rocketseat.sistema_livraria_api.model.Emprestimo;
import rocketseat.sistema_livraria_api.service.EmprestimoService;

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

}
