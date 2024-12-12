package collection;

import nl.saxion.cds.collection.EmptyCollectionException;
import nl.saxion.cds.datastructures.MyArrayList;
import nl.saxion.cds.datastructures.MyStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMyStack {
    private MyStack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new MyStack<>(new MyArrayList<Integer>());
    }

    @Test
    void GivenEmptyStack_WhenAskingIsEmpty_ThenReturnsTrue() {
        assertTrue(stack.isEmpty());
    }

    @Test
    void GivenNonEmptyStack_WhenAskingIsEmpty_ThenReturnsFalse() {
        stack.push(1);
        assertFalse(stack.isEmpty());
    }

    @Test
    void GivenStack_WhenAskingSize_ThenReturnsRightSize() {
        assertEquals(0, stack.size());
        stack.push(1);
        assertEquals(1, stack.size());
        stack.push(2);
        assertEquals(2, stack.size());
    }

    @Test
    void GivenEmptyStack_WhenPushingValue10_ThenSizeIs1AndValueIs10() {
        stack.push(10);
        assertEquals(1, stack.size());
        assertDoesNotThrow(() -> assertEquals(10, stack.peek()));
    }

    @Test
    void GivenStack_WhenPopping_ThenReturnsPoppedValueAndRightSize() throws EmptyCollectionException {
        stack.push(10);
        stack.push(20);
        assertEquals(20, stack.pop());
        assertEquals(1, stack.size());
        assertEquals(10, stack.pop());
        assertEquals(0, stack.size());
    }

    @Test
    void GivenEmptyStack_WhenPopping_ThenThrowsException() {
        assertThrows(EmptyCollectionException.class, () -> stack.pop(), "Popping from empty stack should throw EmptyCollectionException");
    }

    @Test
    void GivenStack_WhenPeeking_ReturnsRightValue() throws EmptyCollectionException {
        stack.push(10);
        stack.push(20);
        assertEquals(20, stack.peek());
        assertEquals(2, stack.size());
    }

    @Test
    void GivenEmptyStack_WhenPeeking_ThrowsException() {
        assertThrows(EmptyCollectionException.class, () -> stack.peek(), "Peeking into an empty stack should throw EmptyCollectionException");
    }

    @Test
    void TestToCoverEmptyGraphViz() {
        assertNull(stack.graphViz("something"));
    }
}


