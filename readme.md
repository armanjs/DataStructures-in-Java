# Data Structures in Java

These implementations are for learning purposes. The implementation may be less efficient than that in the Java standard library.

# Implementations

---

## Binary Search Tree

A binary search tree (BST), also called an ordered or sorted binary tree, is a rooted binary tree whose internal nodes each store a key greater than all the keys in the node's left subtree and less than those in its right subtree. A binary tree is a type of data structure for storing data such as numbers in an organized way. Binary search trees allow binary search for fast lookup, addition and removal of data items, and can be used to implement dynamic sets and lookup tables. The order of nodes in a BST means that each comparison skips about half of the remaining tree, so the whole lookup takes time proportional to the binary logarithm of the number of items stored in the tree. This is much better than the linear time required to find items by key in an (unsorted) array, but slower than the corresponding operations on hash tables.

This binary search tree is not limited to a specific data type. i.e. the tree can store integer, double, char and String.

![Binary search tree](https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Binary_search_tree.svg/400px-Binary_search_tree.svg.png)

#### Time complexity in big O notation

| Algorithm | Average  | Worst case |
| --------- | -------- | ---------- |
| Space     | O(n)     | O(n)       |
| Search    | O(log n) | O(n)       |
| Insert    | O(log n) | O(n)       |
| Delete    | O(log n) | O(n)       |

#### Output:

```
Inorder (sorted): Adam Daniel George Jones Michael Peter Tom
Postorder: Daniel Adam Jones Peter Tom Michael George
Preorder: George Adam Daniel Michael Jones Tom Peter
The number of nodes is 7
Is Peter in the tree? true
A path from the root to Peter is: George Michael Tom Peter
Inorder (sorted): 1 2 3 4 5 6 7 8
```

---

## Hashmap

A hash map (hash table) is a data structure that implements an associative array abstract data type, a structure that can map keys to values. A hash table uses a hash function to compute an index, also called a hash code, into an array of buckets or slots, from which the desired value can be found. During lookup, the key is hashed and the resulting hash indicates where the corresponding value is stored.

![Hashtable image](https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/Hash_table_3_1_1_0_1_0_0_SP.svg/630px-Hash_table_3_1_1_0_1_0_0_SP.svg.png)

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

---

## Linked List

A linked list is a linear collection of data elements whose order is not given by their physical placement in memory. Instead, each element points to the next. It is a data structure consisting of a collection of nodes which together represent a sequence. In its most basic form, each node contains: data, and a reference (in other words, a link) to the next node in the sequence. This structure allows for efficient insertion or removal of elements from any position in the sequence during iteration. More complex variants add additional links, allowing more efficient insertion or removal of nodes at arbitrary positions. A drawback of linked lists is that access time is linear (and difficult to pipeline). Faster access, such as random access, is not feasible. Arrays have better cache locality compared to linked lists.

![Linked list image](https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Singly-linked-list.svg/816px-Singly-linked-list.svg.png)

### Setup

In this project we will be looking into implementing linked lists. After
creating our Node and our MyLinkedList, we start adding methods that can
take full advantage of the linked list structure. We have to be careful when
setting our tail since failure to properly setting it up can cause null pointer
exception. As instructed we start with 10 objects in the list and test our
defined methods.

## Two Way Linked List

In a two way linked list (doubly linked list), each node contains, besides the next-node link, a second link field pointing to the 'previous' node in the sequence. The two links may be called 'forward('s') and 'backwards', or 'next' and 'prev'('previous').

![doubly linked list image](https://upload.wikimedia.org/wikipedia/commons/thumb/5/5e/Doubly-linked-list.svg/1220px-Doubly-linked-list.svg.png)

### Setup

In this project we are trying to test the two way linked list
by adding an extra data field in the node class. By adding the
"previous" data field, we are basically enabling a two way connections
between each of the nodes compered to a single forward way.
Each node is able to communicate with both the previous and the next element
of their neighbour nodes.

---

## Sort Algorithms

A sorting algorithm is an algorithm that puts elements of a list in a certain order. The most frequently used orders are numerical order and lexicographical order. Efficient sorting is important for optimizing the efficiency of other algorithms (such as search and merge algorithms) that require input data to be in sorted lists.

## Sort-Algorithm-Benchmark

In this program we will be investigating different types of sorting methods
and see which one is the fastest (most efficient). We first start with a list of
50K elements and move our way up to 300K elements with an increment of 50K; each having
random integers to be sorted.

Once that's created, we call each method on all the lists
and time them using the currentTimeMills method. To prevent the system from overflowing,
we copy our original list into another and continue. Once all is finished, we print
them nice and neat in a table.

Sorting Benchmarks in **milliseconds**

| Array Size | Selection | Merge | Quick | Heap | Radix |
| ---------- | --------- | ----- | ----- | ---- | ----- |
| 50,000     | 2044      | 81    | 50    | 211  | 25    |
| 100,000    | 4385      | 16    | 13    | 82   | 21    |
| 150,000    | 3862      | 32    | 20    | 94   | 23    |
| 200,000    | 7087      | 37    | 28    | 166  | 32    |
| 250,000    | 10495     | 46    | 38    | 300  | 58    |
| 300,000    | 16047     | 63    | 45    | 266  | 38    |

## Selection

The algorithm divides the input list into two parts: a sorted sublist of items which is built up from left to right at the front (left) of the list and a sublist of the remaining unsorted items that occupy the rest of the list. Initially, the sorted sublist is empty and the unsorted sublist is the entire input list. The algorithm proceeds by finding the smallest (or largest, depending on sorting order) element in the unsorted sublist, exchanging (swapping) it with the leftmost unsorted element (putting it in sorted order), and moving the sublist boundaries one element to the right.

![selection sort Gif](https://upload.wikimedia.org/wikipedia/commons/9/94/Selection-Sort-Animation.gif)

#### Properties

Data structure: Array

| Performance                 | Comparison                    |
| --------------------------- | ----------------------------- |
| Worst-case performance      | О(n2) comparisons, О(n) swaps |
| Best-case performance       | О(n2) comparisons, O(1) swaps |
| Average performance         | О(n2) comparisons, О(n) swaps |
| Worst-case space complexity | O(1) auxiliary                |

## Merge

In computer science, merge sort (also commonly spelled mergesort) is an efficient, general-purpose, comparison-based sorting algorithm. Most implementations produce a stable sort, which means that the order of equal elements is the same in the input and output. Merge sort is a divide and conquer algorithm that was invented by John von Neumann in 1945.A detailed description and analysis of bottom-up mergesort appeared in a report by Goldstine and von Neumann as early as 1948.

![merge sort Gif](https://upload.wikimedia.org/wikipedia/commons/c/cc/Merge-sort-example-300px.gif)

#### Properties

Data structure: Array

| Performance                 | Comparison                                                       |
| --------------------------- | ---------------------------------------------------------------- |
| Worst-case performance      | O(n log n)                                                       |
| Best-case performance       | O(n log n) typical, O(n) natural variant                         |
| Average performance         | O(n log n)                                                       |
| Worst-case space complexity | О(n) total with O(n) auxiliary, O(1) auxiliary with linked lists |

## Quicksort

Quicksort is a divide-and-conquer algorithm. It works by selecting a 'pivot' element from the array and partitioning the other elements into two sub-arrays, according to whether they are less than or greater than the pivot. The sub-arrays are then sorted recursively. This can be done in-place, requiring small additional amounts of memory to perform the sorting.

![quicksort sort Gif](https://upload.wikimedia.org/wikipedia/commons/6/6a/Sorting_quicksort_anim.gif)

#### Properties

Data structure: array

| Performance                 | Comparison                                                              |
| --------------------------- | ----------------------------------------------------------------------- |
| Worst-case performance      | O(n2)                                                                   |
| Best-case performance       | O(n log n) (simple partition) O(n) (three-way partition and equal keys) |
| Average performance         | O(n log n)                                                              |
| Worst-case space complexity | O(n) auxiliary (naive) O(log n) auxiliary (Sedgewick 1978)              |

## Heapsort

Heapsort can be thought of as an improved selection sort: like selection sort, heapsort divides its input into a sorted and an unsorted region, and it iteratively shrinks the unsorted region by extracting the largest element from it and inserting it into the sorted region. Unlike selection sort, heapsort does not waste time with a linear-time scan of the unsorted region; rather, heap sort maintains the unsorted region in a heap data structure to more quickly find the largest element in each step.

![heapsort sort Gif](https://upload.wikimedia.org/wikipedia/commons/1/1b/Sorting_heapsort_anim.gif)

Data structure: array

| Performance                 | Comparison                                      |
| --------------------------- | ----------------------------------------------- |
| Worst-case performance      | O(n log n)                                      |
| Best-case performance       | O(n log n) (distinct keys) or O(n) (equal keys) |
| Average performance         | O(n log n)                                      |
| Worst-case space complexity | O(n) total O(1) auxiliary                       |

## Radix

Radix sort is a non-comparative sorting algorithm. It avoids comparison by creating and distributing elements into buckets according to their radix. For elements with more than one significant digit, this bucketing process is repeated for each digit, while preserving the ordering of the prior step, until all digits have been considered. For this reason, radix sort has also been called bucket sort and digital sort.

Data structure: array

| Performance                 | Comparison                                                          |
| --------------------------- | ------------------------------------------------------------------- |
| Worst-case performance      | O(w . n), where w is the number of bits required to store each key. |
| Worst-case space complexity | O(w + n)                                                            |

---

## Sources

- [Selection sort](https://en.wikipedia.org/wiki/Selection_sort)
- [Merge sort](https://en.wikipedia.org/wiki/Merge_sort)
- [Quicksort](https://en.wikipedia.org/wiki/Quicksort)
- [Radix sort](https://en.wikipedia.org/wiki/Radix_sort)
- [Binary Search Tree](https://en.wikipedia.org/wiki/Binary_search_tree)
- [Linked List and images](https://en.wikipedia.org/wiki/Linked_list)
- [Hash table](https://en.wikipedia.org/wiki/Hash_table)
