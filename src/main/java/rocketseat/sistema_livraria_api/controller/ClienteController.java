package rocketseat.sistema_livraria_api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocketseat.sistema_livraria_api.dto.ClienteDTO;
import rocketseat.sistema_livraria_api.dto.ErrorMessage;
import rocketseat.sistema_livraria_api.exception.ClienteConflictException;
import rocketseat.sistema_livraria_api.model.Cliente;
import rocketseat.sistema_livraria_api.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping("/")
    public ResponseEntity<?> addNewCliente(@RequestBody ClienteDTO clienteDTO) {
        try {
            Cliente cliente = service.createNewCliente(clienteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(cliente);

        } catch (ClienteConflictException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(e.getMessage()));
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllClientes(
            @RequestParam(required = false) Integer id
    ) {
        try {
            if (id == null) {
                List<Cliente> clientes = service.getAllCliente();
                return ResponseEntity.status(HttpStatus.OK).body(clientes);
            }
            Cliente cliente = service.getClienteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(cliente);
        } catch (ClienteConflictException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(e.getMessage()));
        }
    }
}
