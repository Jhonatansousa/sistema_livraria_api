package rocketseat.sistema_livraria_api.exception;

public class LivroNaoEncontradoException extends RuntimeException {
    public LivroNaoEncontradoException(String message) {
        super(message);
    }
}
