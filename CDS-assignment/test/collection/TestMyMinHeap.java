package collection;

import nl.saxion.cds.collection.EmptyCollectionException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import nl.saxion.cds.datastructures.MyMinHeap;

public class TestMyMinHeap {
    @Test
    void GivenEmptyHeap_WhenIsEmptyCalled_ThenReturnsTrue() {
        MyMinHeap<Integer> minHeap = new MyMinHeap<>();
        assertTrue(minHeap.isEmpty());
    }

    @Test
    void GivenHeapWithElements_WhenIsEmptyCalled_ThenReturnsFalse() {
        MyMinHeap<Integer> minHeap = new MyMinHeap<>();
        minHeap.enqueue(10);
        assertFalse(minHeap.isEmpty());
    }

    @Test
    void GivenEmptyHeap_WhenDequeueCalled_ThenThrowsException() {
        MyMinHeap<Integer> minHeap = new MyMinHeap<>();
        assertThrows(EmptyCollectionException.class, minHeap::dequeue);
    }

    @Test
    void GivenHeapWithSingleElement_WhenDequeueCalled_ThenReturnsThatElement() {
        MyMinHeap<Integer> minHeap = new MyMinHeap<>();
        minHeap.enqueue(15);
        assertEquals(15, minHeap.dequeue());
        assertTrue(minHeap.isEmpty());
    }

    @Test
    void GivenHeapWithMultipleElements_WhenDequeueCalled_ThenReturnsMinimumElement() {
        MyMinHeap<Integer> minHeap = new MyMinHeap<>();
        minHeap.enqueue(20);
        minHeap.enqueue(10);
        minHeap.enqueue(15);

        assertEquals(10, minHeap.dequeue());
        assertEquals(15, minHeap.dequeue());
        assertEquals(20, minHeap.dequeue());
        assertTrue(minHeap.isEmpty());
    }

    @Test
    void GivenArray_WhenConstructed_ThenHeapPropertyMaintained() {
        Integer[] elements = {20, 15, 30, 5, 10};
        MyMinHeap<Integer> minHeap = new MyMinHeap<>(elements);

        assertEquals(5, minHeap.dequeue());
        assertEquals(10, minHeap.dequeue());
    }

    @Test
    void GivenHeap_WhenEnqueueCalled_ThenHeapPropertyMaintained() {
        MyMinHeap<Integer> minHeap = new MyMinHeap<>();
        minHeap.enqueue(15);
        minHeap.enqueue(20);
        minHeap.enqueue(10);

        assertEquals(10, minHeap.peek());
    }

    @Test
    void GivenNonEmptyHeap_WhenDecreaseKeyCalled_ThenHeapPropertyMaintained() {
        MyMinHeap<Integer> minHeap = new MyMinHeap<>(new Integer[]{15, 30, 25});

        minHeap.decreaseKey(2, 10);
        assertEquals(10, minHeap.peek());
    }

    @Test
    void GivenInvalidDecreaseKey_WhenDecreaseKeyCalled_ThenThrowsException() {
        MyMinHeap<Integer> minHeap = new MyMinHeap<>();
        minHeap.enqueue(20);
        minHeap.enqueue(15);

        assertThrows(IllegalArgumentException.class, () -> minHeap.decreaseKey(1, 25));
    }

    @Test
    void GivenHeapAtCapacity_WhenEnqueueCalled_ThenHeapResizes() {
        MyMinHeap<Integer> minHeap = new MyMinHeap<>();

        for (int i = 0; i < 16; i++) {
            minHeap.enqueue(i);
        }
        minHeap.enqueue(-1);

        assertEquals(-1, minHeap.peek());
    }
}