package rocketseat.sistema_livraria_api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocketseat.sistema_livraria_api.dto.EmprestimoRequestDTO;
import rocketseat.sistema_livraria_api.exception.ClienteNaoEncontradoException;
import rocketseat.sistema_livraria_api.exception.LivroNaoEncontradoException;
import rocketseat.sistema_livraria_api.model.Cliente;
import rocketseat.sistema_livraria_api.model.Emprestimo;
import rocketseat.sistema_livraria_api.model.Livro;
import rocketseat.sistema_livraria_api.repo.ClienteRepo;
import rocketseat.sistema_livraria_api.repo.EmprestimoRepo;
import rocketseat.sistema_livraria_api.repo.LivroRepo;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepo emprestimoRepo;
    @Autowired
    private LivroRepo livroRepo;
    @Autowired
    private ClienteRepo clienteRepo;

    public Emprestimo createEmprestimo(EmprestimoRequestDTO emprestimoDTO) {
        Livro livro = livroRepo.findById(emprestimoDTO.idLivro())
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro nao encontrado"));
        Cliente cliente = clienteRepo.findById(emprestimoDTO.idCliente())
                .orElseThrow(() -> new ClienteNaoEncontradoException("Livro nao encontrado"));

        Emprestimo emprestimo = new Emprestimo();
        livro.setDisponivel(false);
        emprestimo.setLivro(livro);
        emprestimo.setCliente(cliente);
        return emprestimoRepo.save(emprestimo);
    }
}
