package nl.saxion.cds.collection;

public class DuplicateKeyException extends RuntimeException {
    public DuplicateKeyException(String key) {
        super("a duplicate key \"" + key + "\" is not allowed.");
    }
}
