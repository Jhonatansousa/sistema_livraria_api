package rocketseat.sistema_livraria_api.exception;

public class AutorConflictException extends RuntimeException {
    public AutorConflictException(String message) {
        super(message);
    }
}
