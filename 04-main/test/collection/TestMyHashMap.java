package collection;

import nl.saxion.cds.collection.DuplicateKeyException;
import nl.saxion.cds.collection.EmptyCollectionException;
import nl.saxion.cds.collection.KeyNotFoundException;
import nl.saxion.cds.collection.SaxList;
import nl.saxion.cds.datastructures.MyArrayList;
import nl.saxion.cds.datastructures.MyHashMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class TestMyHashMap {
    private MyHashMap<Integer, String> map;

    @BeforeEach
    void setUp() {
        map = new MyHashMap<>();
    }

    void giveMapWith3Elements() {
        map.add(1, "value1");
        map.add(2, "value2");
        map.add(3, "value3");
    }

    @Test
    void GivenEmptyList_WhenInitializing_ThenCapacityIs16AndSizeIs0() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }

    @Test
    void GivenEmptyList_WhenTryingToAccessElements_ThenThrowsException() {
        assertThrows(EmptyCollectionException.class, () -> map.contains(1));
        assertThrows(EmptyCollectionException.class, () -> map.get(1));
        assertThrows(EmptyCollectionException.class, () -> map.remove(1));
        assertThrows(EmptyCollectionException.class, () -> map.getKeys());
        assertThrows(EmptyCollectionException.class, () -> map.values());
    }

    @Test
    void GivenNonEmptyList_WhenCheckingExistingKey_ThenReturnsTrue() {
        giveMapWith3Elements();
        assertEquals(3, map.size());
        assertTrue(map.contains(2));
    }

    @Test
    void GivenNonEmptyList_WhenCheckingNonExistentKey_ThenReturnsFalse() {
        giveMapWith3Elements();
        assertFalse(map.contains(4));
    }

    @Test
    void GivenNonEmptyList_WhenGettingExistingKey_ThenReturnsRightValue() {
        giveMapWith3Elements();
        assertEquals("value2", map.get(2));
        assertEquals("value3", map.get(3));
    }

    @Test
    void GivenNonEmptyList_WhenGettingNonExistentKey_ThenThrowsException() {
        giveMapWith3Elements();
        assertEquals(3, map.size());
        assertThrows(KeyNotFoundException.class, () -> map.get(5));
    }

    @Test
    void GivenEmptyList_WhenAddingMultipleElements_ThenElementsGetAddedToHashMap() {
        giveMapWith3Elements();
        assertEquals(3, map.size());
        assertTrue(map.contains(1));
        assertTrue(map.contains(2));
        assertTrue(map.contains(3));
    }

    @Test
    void GivenListOf11Elements_WhenAddingElement_ThenResizes() {
        int initialCapacity = 16; //initial capacity
        double loadFactorThreshold = 0.75;
        int maxBeforeResize = (int) (initialCapacity * loadFactorThreshold); //12 elements before resizing

        for (int i = 0; i < maxBeforeResize; i++) { //Adding elements just below threshold
            map.add(i, "value" + i);
        }
        assertEquals(maxBeforeResize, map.size()); //Ensure size is what we expect

        map.add(maxBeforeResize, "value" + maxBeforeResize); //Adding last element to trigger resize

        assertEquals(maxBeforeResize + 1, map.size()); //Ensure size is what we expect = 13

        for (int i = 0; i <= maxBeforeResize; i++) { //Verifying if resize happened and list size and element are intact
            assertEquals("value" + i, map.get(i));
        }
    }
    @Test
    void GivenEmptyList_WhenAddingNullAsKey_ThenThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> map.add(null, "value"));
    }

    @Test
    void GivenNonEmptyList_WhenAddingDuplicateKey_ThenThrowException() {
        map.add(1, "value1");
        assertTrue(map.contains(1));
        assertThrows(DuplicateKeyException.class, () -> map.add(1, "value1"));
    }

    @Test
    void GivenKeysWithSameHashCode_WhenAdding_ThenUsesLinearProbing() {
        MyHashMap<String, String> testMap = new MyHashMap<>();

        //These 2 strings produce the same hashcode
        String key1 = "FB";
        String key2 = "Ea";

        testMap.add(key1, "value1");
        testMap.add(key2, "value2"); //Should be placed in a different bucket via linear probing

        assertEquals("value1", testMap.get(key1));
        assertEquals("value2", testMap.get(key2));
    }

    @Test
    void GivenNonEmptyList_WhenRemovingKey_ThenRemovesSuccesfully() {
        giveMapWith3Elements();
        assertEquals(3, map.size());

        assertEquals("value2", map.remove(2));
        assertFalse(map.contains(2));
        assertEquals(2, map.size());
    }

    @Test
    void GivenNonEmptyList_WhenRemovingNonExistentKey_ThenThrowsException() {
        giveMapWith3Elements();
        assertEquals(3, map.size());
        assertThrows(KeyNotFoundException.class, () -> map.remove(4));
    }

    @Test
    void GivenNonEmptyList_WhenGettingKeys_ReturnsAllKeys() {
        giveMapWith3Elements();
        SaxList<Integer> keys = map.getKeys();

        assertEquals(3, keys.size());
        assertTrue(keys.contains(1));
        assertTrue(keys.contains(2));
        assertTrue(keys.contains(3));
    }

    @Test
    void GivenNonEmptyList_WhenGettingValues_ReturnAllValues() {
        giveMapWith3Elements();
        SaxList<String> values = map.values();

        assertEquals(3, map.size());
        assertTrue(values.contains("value1"));
        assertTrue(values.contains("value2"));
        assertTrue(values.contains("value3"));
    }

}
