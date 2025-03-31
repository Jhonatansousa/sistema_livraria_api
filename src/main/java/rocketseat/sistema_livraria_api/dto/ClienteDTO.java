package rocketseat.sistema_livraria_api.dto;

import java.time.LocalDate;

public record ClienteDTO(String email, LocalDate dataNascimento, String nome) {
}
