package collection;

import nl.saxion.cds.collection.EmptyCollectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import nl.saxion.cds.datastructures.MyPriorityQueue;


public class TestMyPriorityQueue {
    private MyPriorityQueue<Integer> queue;

    @BeforeEach
    public void setUp() {
        queue = new MyPriorityQueue<>();
    }

    @Test
    public void GivenEmptyQueue_WhenEnqueueElements_ThenPeekReturnsSmallestElement() {
        queue.enqueue(10);
        queue.enqueue(5);
        queue.enqueue(15);

        assertEquals(5, queue.peek());
    }

    @Test
    public void GivenQueueWithElements_WhenDequeue_ThenReturnsSmallestAndUpdatesFront() {
        queue.enqueue(20);
        queue.enqueue(3);
        queue.enqueue(8);

        assertEquals(3, queue.dequeue());
        assertEquals(8, queue.peek());
    }

    @Test
    public void GivenQueueWithElements_WhenPeek_ThenReturnsSmallestWithoutRemoving() {
        queue.enqueue(7);
        queue.enqueue(1);
        queue.enqueue(9);

        assertEquals(1, queue.peek());
        assertEquals(3, queue.size());
    }

    @Test
    public void GivenEmptyQueue_WhenDequeue_ThenThrowsException() {
        assertThrows(EmptyCollectionException.class, () -> queue.dequeue());
    }

    @Test
    public void GivenEmptyQueue_WhenCheckIsEmpty_thenReturnsTrue() {
        assertTrue(queue.isEmpty());

        queue.enqueue(50);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void GivenEmptyQueue_WhenPeeking_ThenThrowsException() {
        assertThrows(EmptyCollectionException.class, () -> queue.peek());
    }

    @Test
    void TestToCoverEmptyGraphViz() {
        assertNull(queue.graphViz("something"));
    }

}
