package collection;

import nl.saxion.cds.collection.EmptyCollectionException;
import nl.saxion.cds.datastructures.MyArrayList;
import nl.saxion.cds.datastructures.MyStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMyStack {
    private static final int BIG_NUMBER_OF_ELEMENTS = 5000;
    private MyStack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new MyStack<>(new MyArrayList<Integer>());
    }

    @Test
    void testIsEmptyTrue() {
        assertTrue(stack.isEmpty(), "Stack is empty");
    }

    @Test
    void testIsEmptyFalse() {
        stack.push(1);
        assertFalse(stack.isEmpty(), "Stack is not empty ");
    }

    @Test
    void testSize() {
        assertEquals(0, stack.size(), "Stack is empty");
        stack.push(1);
        assertEquals(1, stack.size(), "Stack size should be 1 after one push");
        stack.push(2);
        assertEquals(2, stack.size(), "Stack size should be 2 after two pushes");
    }

    @Test
    void testPush() {
        stack.push(10);
        assertEquals(1, stack.size(), "Stack size should be 1 after one push");
        assertDoesNotThrow(() -> assertEquals(10, stack.peek(), "Top element should be 10 after pushing 10"));
    }

    @Test
    void testPop() throws EmptyCollectionException {
        stack.push(10);
        stack.push(20);
        assertEquals(20, stack.pop(), "Popped value should be 20");
        assertEquals(1, stack.size(), "Stack size should be 1 after one pop");
        assertEquals(10, stack.pop(), "Popped value should be 10 after second pop");
        assertEquals(0, stack.size(), "Stack size should be 0 after popping all elements");
    }

    @Test
    void testPopEmptyStackThrowsException() {
        assertThrows(EmptyCollectionException.class, () -> stack.pop(), "Popping from empty stack should throw EmptyCollectionException");
    }

    @Test
    void testPeek() throws EmptyCollectionException {
        stack.push(10);
        stack.push(20);
        assertEquals(20, stack.peek(), "Peeked value should be 20 after pushing 10 and 20");
        assertEquals(2, stack.size(), "Stack size should remain 2 after peek");
    }

    @Test
    void testPeekEmptyStackThrowsException() {
        assertThrows(EmptyCollectionException.class, () -> stack.peek(), "Peeking into an empty stack should throw EmptyCollectionException");
    }
}


