package notes.exception;

public class NotaNotFoundException extends RuntimeException {
    public NotaNotFoundException(Long id) {
        super("Could not find note " + id);
      }
}