package rocketseat.sistema_livraria_api.dto;

import rocketseat.sistema_livraria_api.model.Livro;

public record LivroDTO(String titulo, String autorNome, Livro.Genero genero) {

}
