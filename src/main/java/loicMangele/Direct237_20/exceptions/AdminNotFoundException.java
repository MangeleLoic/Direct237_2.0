package loicMangele.Direct237_20.exceptions;

public class AdminNotFoundException extends RuntimeException {
    public AdminNotFoundException(Long id) {
            super("L'admin con id: " + id + " non è stato trovato.");
    }
}
