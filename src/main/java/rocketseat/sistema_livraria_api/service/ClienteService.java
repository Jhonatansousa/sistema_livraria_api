package rocketseat.sistema_livraria_api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocketseat.sistema_livraria_api.dto.ClienteDTO;
import rocketseat.sistema_livraria_api.exception.ClienteConflictException;
import rocketseat.sistema_livraria_api.model.Cliente;
import rocketseat.sistema_livraria_api.repo.ClienteRepo;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepo repo;

    public Cliente createNewCliente(ClienteDTO clienteDTO) {
        Cliente cliente = repo.findByEmailIgnoreCase(clienteDTO.email());
        if (cliente != null) {
            throw new ClienteConflictException("Já existe um cliente cadastrado com este e-mail");
        }
        Cliente newCliente = new Cliente();
        newCliente.setEmail(clienteDTO.email());
        newCliente.setNome(clienteDTO.nome());
        newCliente.setDataNascimento(clienteDTO.dataNascimento());
        repo.save(newCliente);
        return newCliente;
    }

    public List<Cliente> getAllCliente() {
        return (List<Cliente>) repo.findAll();
    }

    public Cliente getClienteById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new ClienteConflictException("Não existe cliente cadastrado com o id: " + id));
    }
}
