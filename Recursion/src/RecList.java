/**
 * A recursive singly linked list container.
 *
 * @param <T> any type to be put in a list
 */
public class RecList<T extends Comparable<T>> {
    private RecList<T> next;
    private T value;

    public RecList(RecList<T> next, T value) {
        next = null;
        value = null;
    }

    public RecList(T value) {
        this.value = value;
        this.next = null;
    }
    public boolean isEmpty() { return value == null;}

    private boolean contains(RecList<T> list, T value) {
        boolean result;

        if (list.isEmpty()) {
            result = false;
        } else if (list.value.equals(value)) {
            result = true;
        } else {
            result = contains(list.next, value);
        }

        return result;
    }

    public T largestElement() { //Only gets used when list is empty. Otherwise goes to largestElement method (recursive)
        if (isEmpty()) {
            return null;
        } else {
            return largestElement(this.next, this.value);
        }
    }

    private T largestElement(RecList<T> list, T value) {
        T result;

        if (list.isEmpty()) {
            result = value;
        } else if (list.value.compareTo(value) > 0) {
            return largestElement(list.next, list.value);
        } else {
            result = largestElement(list.next, value);
        }
        return result;
    }
}
