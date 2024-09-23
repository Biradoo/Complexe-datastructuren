package nl.saxion.cds.collection;

public class EmptyCollectionException extends RuntimeException {
    public EmptyCollectionException() {
    }
    public EmptyCollectionException(String message) {
        super(message);
    }
}
