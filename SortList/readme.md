# Sort-Algorithm-Benchmark

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

## Sort Algorithms

### Selection

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

### Merge

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

### Quicksort

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

### Heapsort

Heapsort can be thought of as an improved selection sort: like selection sort, heapsort divides its input into a sorted and an unsorted region, and it iteratively shrinks the unsorted region by extracting the largest element from it and inserting it into the sorted region. Unlike selection sort, heapsort does not waste time with a linear-time scan of the unsorted region; rather, heap sort maintains the unsorted region in a heap data structure to more quickly find the largest element in each step.

![heapsort sort Gif](https://upload.wikimedia.org/wikipedia/commons/1/1b/Sorting_heapsort_anim.gif)

Data structure: array

| Performance                 | Comparison                                      |
| --------------------------- | ----------------------------------------------- |
| Worst-case performance      | O(n log n)                                      |
| Best-case performance       | O(n log n) (distinct keys) or O(n) (equal keys) |
| Average performance         | O(n log n)                                      |
| Worst-case space complexity | O(n) total O(1) auxiliary                       |

### Radix

Radix sort is a non-comparative sorting algorithm. It avoids comparison by creating and distributing elements into buckets according to their radix. For elements with more than one significant digit, this bucketing process is repeated for each digit, while preserving the ordering of the prior step, until all digits have been considered. For this reason, radix sort has also been called bucket sort and digital sort.

Data structure: array

| Performance                 | Comparison                                                          |
| --------------------------- | ------------------------------------------------------------------- |
| Worst-case performance      | O(w . n), where w is the number of bits required to store each key. |
| Worst-case space complexity | O(w + n)                                                            |

---

## Sources

- Selection sort - [Wikipedia](https://en.wikipedia.org/wiki/Selection_sort)
- Merge sort - [Wikipedia](https://en.wikipedia.org/wiki/Merge_sort)
- Quicksort - [Wikipedia](https://en.wikipedia.org/wiki/Quicksort)
- Selection sort - [Wikipedia](https://en.wikipedia.org/wiki/Selection_sort)
- Radix sort - [Wikipedia](https://en.wikipedia.org/wiki/Radix_sort)
