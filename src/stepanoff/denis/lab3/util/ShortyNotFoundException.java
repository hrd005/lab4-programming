package stepanoff.denis.lab3.util;

public class ShortyNotFoundException extends RuntimeException {

    public ShortyNotFoundException(String shortyName) {
        super("404 " + shortyName + " not found");
    }
}
