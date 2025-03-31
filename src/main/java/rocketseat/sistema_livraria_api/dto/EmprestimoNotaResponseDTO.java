package rocketseat.sistema_livraria_api.dto;

import java.time.LocalDateTime;

public record EmprestimoNotaResponseDTO (
        Integer idEmprestimo,
        String tituloLivro,
        String nomeAutor,
        String genero,
        Integer idCliente,
        LocalDateTime dataEmprestimo,
        LocalDateTime dataDevolucao
) {

}
