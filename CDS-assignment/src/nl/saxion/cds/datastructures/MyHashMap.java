package nl.saxion.cds.datastructures;

import nl.saxion.cds.collection.*;

public class MyHashMap<K, V> implements SaxHashMap<K, V> {
    private static final int INITIAL_CAPACITY = 16; //Initial capacity of the Hashtable;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75; //To resize
    private int size;
    private Bucket<K, V>[] buckets; //Array to store Key-Value pairs

    public MyHashMap() {
        this.buckets = new Bucket[INITIAL_CAPACITY]; //Initialize Bucket array
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String graphViz(String name) {
        return null;
    }

    @Override
    public boolean contains(K key) {
        int index = findIndex(key); //Find key of index
        return index != -1; //Return true if key is found (-1 means no key found)
    }

    @Override
    public V get(K key) {
        if (isEmpty()) throw new EmptyCollectionException();

        int index = findIndex(key);
        if (index == -1) {
            throw new KeyNotFoundException("Key not found: " + key);
        }
        return buckets[index].value;
    }

    @Override
    public void add(K key, V value) throws DuplicateKeyException {
        if (key == null) throw new IllegalArgumentException(); //No null allowed because check is done with != null.

        if (size >= buckets.length * LOAD_FACTOR_THRESHOLD) {
            resize(); //Resize HashMap if needed
        }
        int index = getHashIndex(key); //Get hash index

        while (buckets[index] != null) { //Linear probing to find the key
            if (buckets[index].key.equals(key)) {
                throw new DuplicateKeyException(""+ key);
            }
            index = (index + 1) % buckets.length; //Linear probing: move to the next index
        }

        buckets[index] = new Bucket<>(key, value); //If an empty spot is found insert Key-Value pair
        size++;
    }

    public void put(K key, V value) {
        //If the key already exists, remove the old entry before adding the new one
        if (contains(key)) {
            remove(key);
        }
        add(key, value);
    }

    @Override
    public V remove(K key) throws KeyNotFoundException {
        if (isEmpty()) throw new EmptyCollectionException();

        int index = findIndex(key); //Find index of key

        if (index == -1) throw new KeyNotFoundException("Key not found: " + key);

        V removedValue = buckets[index].value; //Store value to return
        buckets[index] = null; //Mark bucket as empty
        size--;

        rehashFrom(index); //Rehash elements after index to ensure they are findable

        return removedValue; //Return removed value;
    }

    @Override
    public MyArrayList<K> getKeys() {
        if (isEmpty()) throw new EmptyCollectionException();

        MyArrayList<K> keys = new MyArrayList<>(); //List to store all keys
        //Traverse each bucket in the bucket array
        for (Bucket<K, V> bucket : buckets) {
            if (bucket != null) {
                keys.addLast(bucket.key); //Add key to list of keys
            }
        }
        return keys; //Return list of keys
    }


    public MyArrayList<V> values() { //Get a list of all values in the HashMap
        if (isEmpty()) throw new EmptyCollectionException();
        MyArrayList<V> valuesList = new MyArrayList<>(); //Create a list to store the values

        for (Bucket<K, V> bucket : buckets) { //Iterate through the buckets array
            if (bucket != null) {
                valuesList.addLast(bucket.value); //Add the value to the list
            }
        }
        return valuesList; // Return the list of values
    }

    private int getHashIndex(K key) { //Hash function to get bucket index
        return Math.abs(key.hashCode() % buckets.length); //Modulo length of the bucket array
    }

    private int findIndex(K key) { //To find index of the key in the HashMap
        int index = getHashIndex(key); //Get index

        while (buckets[index] != null) { //Linear probing to find the key
            if (buckets[index].key.equals(key)) {
                return index; //Return index if key is found
            }
            index = (index + 1) % buckets.length; //Linear probing: move to the next index
        }
        return -1; //Return -1 if key is not found
    }

    private void resize() { //Resize when load factor exceeds threshold
        Bucket<K, V>[] oldBuckets = buckets; //Store old buckets
        buckets = new Bucket[oldBuckets.length * 2]; //Double size of ney array

        size = 0; //Reset size first

        for (Bucket<K, V> bucket : oldBuckets) { //Rehash all elements from old bucket array
            if (bucket != null) {
                add(bucket.key, bucket.value);
            }
        }
    }

    private void rehashFrom(int startIndex) {
        int index = (startIndex + 1) % buckets.length; //Start rehashing from next element
        while (buckets[index] != null) {
            Bucket<K, V> bucketToRehash = buckets[index];
            buckets[index] = null; //Mark current bucket as empty
            size--;
            add(bucketToRehash.key, bucketToRehash.value); //Rehash element
            index = (index + 1) % buckets.length; //Linear probing
        }
    }

    private class Bucket<K, V> { //Class to store key-value pairs
        private K key;
        private V value;

        public Bucket(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


}
