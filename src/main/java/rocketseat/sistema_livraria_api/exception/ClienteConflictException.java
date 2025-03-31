package rocketseat.sistema_livraria_api.exception;

public class ClienteConflictException extends RuntimeException {
    public ClienteConflictException(String message) {
        super(message);
    }
}
