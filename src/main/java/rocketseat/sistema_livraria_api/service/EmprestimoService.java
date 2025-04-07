package rocketseat.sistema_livraria_api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocketseat.sistema_livraria_api.dto.EmprestimoRequestDTO;
import rocketseat.sistema_livraria_api.exception.ClienteNaoEncontradoException;
import rocketseat.sistema_livraria_api.exception.EmprestimoException;
import rocketseat.sistema_livraria_api.exception.LivroNaoEncontradoException;
import rocketseat.sistema_livraria_api.model.Cliente;
import rocketseat.sistema_livraria_api.model.Emprestimo;
import rocketseat.sistema_livraria_api.model.Livro;
import rocketseat.sistema_livraria_api.repo.ClienteRepo;
import rocketseat.sistema_livraria_api.repo.EmprestimoRepo;
import rocketseat.sistema_livraria_api.repo.LivroRepo;

import java.time.LocalDateTime;
import java.util.List;

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

    //não preciso necessariamente tratar erros em um metodo getALL;
    public Iterable<Emprestimo> getAllEmprestimos() {
        return  emprestimoRepo.findAll();
    }

    public Emprestimo updateEmprestimo(Integer id) {
        Emprestimo emprestimo = emprestimoRepo.findById(id)
                .orElseThrow(() -> new EmprestimoException("livro nao encontrado"));
        emprestimo.getLivro().setDisponivel(true);
        emprestimo.setDataDevolucao(LocalDateTime.now());
        return emprestimoRepo.save(emprestimo);
    }

    public Iterable<Emprestimo> getEmprestimoByDateRange(LocalDateTime dataInicio, LocalDateTime dataFim){
        return emprestimoRepo.findByDataEmprestimoBetween(dataInicio, dataFim);
    }

//    public List<ClienteWithEmprestimoDTO> getClientesEmprestimo() {
//        List<Emprestimo> clientesEmprestimo = getAllEmprestimos();
//        List<Livro> livros = livroRepo.findAll();
//        List<Emprestimo> response = clientesEmprestimo.stream()
//                .filter(c-> c.getDataEmprestimo() != null);
//    }
}
