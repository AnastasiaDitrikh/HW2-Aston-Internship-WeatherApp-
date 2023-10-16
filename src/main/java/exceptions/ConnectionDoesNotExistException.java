package exceptions;

/**
 * Формирует исключение, если есть проблемы с подключением
 */
public class ConnectionDoesNotExistException extends RuntimeException {
    public ConnectionDoesNotExistException(String message) {
        super(message);
    }
}