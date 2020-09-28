# Hashmap

A hash map (hash table) is a data structure that implements an associative array abstract data type, a structure that can map keys to values. A hash table uses a hash function to compute an index, also called a hash code, into an array of buckets or slots, from which the desired value can be found. During lookup, the key is hashed and the resulting hash indicates where the corresponding value is stored.

![Hashtable image](https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/Hash_table_3_1_1_0_1_0_0_SP.svg/630px-Hash_table_3_1_1_0_1_0_0_SP.svg.png)

Source: [Wikipedia](https://en.wikipedia.org/wiki/Hash_table)

#### Time complexity in big O notation

| Algorithm | Average | Worst case |
| --------- | ------- | ---------- |
| Space     | O(n)    | O(n)       |
| Search    | O(1)    | O(n)       |
| Insert    | O(1)    | O(n)       |
| Delete    | O(1)    | O(n)       |

### Setup

In this project we will be looking into hashing and how we can take
advantage of it's efficiency. We first have to define the "Default
initial capacity and the max load factor" since they're crucial.
Then we can start by defining the subclass (Entry) and then implement
the methods necessary. LinkedList and sets are also very important in
this project since their structure helps build the hash structure.