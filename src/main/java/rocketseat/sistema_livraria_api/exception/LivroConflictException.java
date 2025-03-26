package rocketseat.sistema_livraria_api.exception;

public class LivroConflictException extends RuntimeException {
    public LivroConflictException(String message) {
        super(message);
    }
}
