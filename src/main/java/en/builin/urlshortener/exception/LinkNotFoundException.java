package en.builin.urlshortener.exception;

public class LinkNotFoundException extends RuntimeException {

    public LinkNotFoundException(String key) {
        super(String.format("Url with key %s not found", key));
    }
}
