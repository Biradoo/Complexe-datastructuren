package nl.saxion.cds.collection;

public class KeyNotFoundException extends RuntimeException {
    public KeyNotFoundException(String key) {
        super("Key \"" + key + "\" is not found.");
    }
}
