package collection;

import nl.saxion.cds.collection.EmptyCollectionException;
import nl.saxion.cds.datastructures.MyLinkedList;
import nl.saxion.cds.datastructures.MyQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestMyQueue {
    private MyQueue<Integer> queue;
    @BeforeEach
    void setUp() {
        queue = new MyQueue<>();
    }

    @Test
    void GivenEmptyList_WhenAddingElement_ThenIncreasesSize() {
        queue.enqueue(1);
        assertEquals(1, queue.size());
    }

    @Test
    void GivenNonEmptyList_WhenRemovingAllElements_ThenReturnsIsEmpty() {
        queue.enqueue(1);
        assertEquals(1, queue.size());

        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    void GivenEmptyList_WhenDequeueing_ThenThrowsException() {
        assertThrows(EmptyCollectionException.class, () -> queue.dequeue());
    }

    @Test
    void GivenEmptyList_WhenPeeking_ThenThrowsException() {
        assertThrows(EmptyCollectionException.class, () -> queue.peek());
    }

    @Test
    void GivenNonEmptyQueue_WhenPeeking_ThenReturnsCorrectElement() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        assertEquals(1, queue.peek());
    }
}
