/* In this project we will be looking into hashing and how we can take
 * advantage of it's efficiency. We first have to define the "Default
 * initial capacity and the max load factor" since they're crucial.
 * Then we can start by defining the subclass (Entry) and then implement
 * the methods necessary. LinkedList and sets are also very important in
 * this project since their structure helps build the hash structure.
 * May 12, 2020
 * Arman Sadeghi
 */

package hashmap;

import java.util.LinkedList;

public class HashMap<K,V> {

    // Define the default hash-table size. Must be a power of 2
    private static int DEFAULT_INITIAL_CAPACITY = 4;

    // Define the maximum hash-table size. 1 << 30 is same as 2^30
    private static int MAX_CAPACITY = 1 << 30;

    // Current hash-table capacity. Capacity is a power of 2
    private int capacity;

    // define the default load factor (lambda = number of elements / number of available space
    private static float DEFAULT_MAX_LOAD_FACTOR = 0.5f;

    // specify a load factor used in the hash-table
    private float loadFactorThreshold;

    // The number of entries in the map
    private int size = 0;

    /** Define an inner class for Entry */
    public static class Entry<K,V> {
        K key;
        V value;

        public Entry(K key, V value){
            this.key = key;
            this.value = value;
        }

        public K getKey(){
            return key;
        }

        public V getValue(){
            return value;
        }

        @Override
        public String toString(){
            return "[" + key + ", " + value + "]";
        }
    }

    // Hash-map is an array with each cell being a linked list
    LinkedList<Entry<K,V>>[] table;

    /** Construct a map with the default capacity and load factor */
    public HashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    /**Construct a map with the specified initial
     capacity and default load factor */
    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    /** Construct a map with the specified initial capacity and load factor */
    public HashMap(int initialCapacity, float loadFactorThreshold) {
        if (initialCapacity > MAX_CAPACITY)
            this.capacity = MAX_CAPACITY;
        else
            this.capacity = trimToPowerOf2(initialCapacity);

        this.loadFactorThreshold = loadFactorThreshold;
        table = new LinkedList[capacity];
    }

    /** remove all entries from this map */
    public void clear() {
        size = 0;
        removeEntries();
    }

    /** Return true if the specified key is in the map */
    public boolean containsKey(K key) {
        if (get(key) != null)
            return true;
        else
            return false;
    }

    /** Return true if this map contains the value */
    public boolean containsValue(V value) {
        for (int i = 0; i < capacity; i++){
            if (table[i] != null) {
                LinkedList<Entry<K,V>> bucket = table[i];
                for (Entry<K,V> entry: bucket) {
                    if (entry.getValue().equals(value))
                        return true;
                }
            }
        }
        return false;
    }

    /** Return a set of entries in the map */
    public java.util.Set<HashMap.Entry<K,V>> entrySet() {
        java.util.Set<HashMap.Entry<K, V>> set =
                new java.util.HashSet<>();

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                for (Entry<K, V> entry: bucket)
                    set.add(entry);
            }
        }

        return set;
    }

    /** Return the value that matches the specified key */
    public V get(K key) {
        int bucketIndex = hash(key.hashCode());
        if (table[bucketIndex] != null) {
            LinkedList<Entry<K, V>> bucket = table[bucketIndex];
            for (Entry<K, V> entry: bucket)
                if (entry.getKey().equals(key))
                    return entry.getValue();
        }

        return null;
    }

     /** Return true if this map contains no entries */
    public boolean isEmpty() {
        return size == 0;
    }

     /** Return a set consisting of the keys in this map */
    public java.util.Set<K> keySet() {
        java.util.Set<K> set = new java.util.HashSet<K>();

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                for (Entry<K, V> entry: bucket)
                    set.add(entry.getKey());
            }
        }

        return set;
    }

    /** Add an entry (key, value) into the map */
    public V put(K key, V value) {
        if (get(key) != null) { // The key is already in the map
            int bucketIndex = hash(key.hashCode());
            LinkedList<Entry<K, V>> bucket = table[bucketIndex];
            for (Entry<K, V> entry: bucket)
                if (entry.getKey().equals(key)) {
                    V oldValue = entry.getValue();
                    // Replace old value with new value
                    entry.value = value;
                    // Return the old value for the key
                    return oldValue;
                }
        }

        // Check load factor
        if (size >= capacity * loadFactorThreshold) {
            if (capacity == MAX_CAPACITY)
                throw new RuntimeException("Exceeding maximum capacity");

            rehash();
        }

        int bucketIndex = hash(key.hashCode());

        // Create a linked list for the bucket if it is not created
        if (table[bucketIndex] == null) {
            table[bucketIndex] = new LinkedList<Entry<K, V>>();
        }

        // Add a new entry (key, value) to hashTable[index]
        table[bucketIndex].add(new HashMap.Entry<K, V>(key, value));

        size++; // Increase size

        return value;
    }

     /** Remove the entries for the specified key */
    public void remove(K key) {
        int bucketIndex = hash(key.hashCode());

        // Remove the first entry that matches the key from a bucket
        if (table[bucketIndex] != null) {
            LinkedList<Entry<K, V>> bucket = table[bucketIndex];
            for (Entry<K, V> entry: bucket)
                if (entry.getKey().equals(key)) {
                    bucket.remove(entry);
                    size--; // Decrease size
                    break; // Remove just one entry that matches the key
                }
        }
    }

    /** Return the number of entries in this map */
    public int size() {
        return size;
    }

     /** Return a set consisting of the values in this map */
    public java.util.Set<V> values() {
        java.util.Set<V> set = new java.util.HashSet<>();

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                for (Entry<K, V> entry: bucket)
                    set.add(entry.getValue());
            }
        }

        return set;
    }

    /** Hash function */
    private int hash(int hashCode) {
        return supplementalHash(hashCode) & (capacity - 1);
    }

    /** Ensure the hashing is evenly distributed */
    private static int supplementalHash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /** Return a power of 2 for initialCapacity */
    private int trimToPowerOf2(int initialCapacity) {
        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity <<= 1;
        }

        return capacity;
    }

    /** Remove all entries from each bucket */
    private void removeEntries() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                table[i].clear();
            }
        }
    }

    /** Rehash the map */
    private void rehash() {
        java.util.Set<Entry<K, V>> set = entrySet(); // Get entries
        capacity <<= 1; // Double capacity
        table = new LinkedList[capacity]; // Create a new hash table
        size = 0; // Reset size to 0

        for (Entry<K, V> entry: set) {
            put(entry.getKey(), entry.getValue()); // Store to new table
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && table[i].size() > 0)
                for (Entry<K, V> entry: table[i])
                    builder.append(entry);
        }

        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();

        System.out.print("Enter (0) for clear, (1) for containsKey, (2) for containsValue\n" +
                "Enter (3) for isEmpty, (4) for keySet, (5) for put, (6) for show map\n" +
                "Enter (7) for remove, (8) for size, (9) for values and (-1) to quit: ");
        java.util.Scanner input = new java.util.Scanner(System.in);
        int option = input.nextInt();
        while (option != -1) {
            if (option == 0) {
                map.clear();
                System.out.print("Your map has been cleared.");
            }
            else if (option == 1) {
                System.out.print("Enter the key to see if the map contains it: ");
                String key = input.next();
                if (map.containsKey(key))
                    System.out.print("Yes! The entered key exists in the map!\n");
                else
                    System.out.print("The entered key was not found in the map.\n");
            }
            else if (option == 2) {
                System.out.print("Enter the value to see if the map contains it: ");
                int value = input.nextInt();
                if (map.containsValue(value))
                    System.out.print("Yes! The entered value exists in the map!\n");
                else
                    System.out.print("The entered value was not found in the map.\n");
            }
            else if (option == 3) {
                if (map.isEmpty())
                    System.out.print("The map indeed is empty.\n");
                else
                    System.out.print("No. The map still contains elements.\n");
            }
            else if (option == 4) {
                System.out.print("Here is the set of keys in the map: " + map.keySet());
                System.out.print("\n");
            }
            else if (option == 5) {
                System.out.print("Enter a key followed by a value to be put in your tree: ");
                String key = input.next();
                int value = input.nextInt();
                map.put(key,value);
            }
            else if (option == 6) {
                System.out.print("Your current map is: " + map);
                System.out.print("\n");
            }
            else if (option == 7) {
                System.out.print("Enter a key to remove element: ");
                String key = input.next();
                if (map.containsKey(key)) {
                    System.out.print("(" + map.get(key) + ") Has been removed from map.\n");
                    map.remove(key);
                } else
                    System.out.print("The entered key (" + key + ") was not found in the map.\n");
            }
            else if (option == 8) {
                System.out.print("The size of the map is: " + map.size);
                System.out.print("\n");
            }
            else if (option == 9) {
                System.out.print("The values in this map are: " + map.values());
                System.out.print("\n");
            }
            System.out.print("Enter a number from (0 - 9) to perform an action on your map or -1 to quit: ");
            option = input.nextInt();
        }

        /* uncomment to Test MyHashMap
        map.put("Smith", 30);
        map.put("Anderson", 31);
        map.put("Lewis", 29);
        map.put("Cook", 29);
        map.put("Smith", 65);

        System.out.println("Entries in map: " + map);

        System.out.println("The age for " + "Lewis is " +
                map.get("Lewis"));

        System.out.println("Is Smith in the map? " +
                map.containsKey("Smith"));
        System.out.println("Is age 33 in the map? " +
                map.containsValue(33));

        map.remove("Smith");
        System.out.println("Entries in map: " + map);

        map.clear();
        System.out.println("Entries in map: " + map); */
    }
}
