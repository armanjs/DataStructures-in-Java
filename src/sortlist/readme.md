# Sort-Algorithm-Benchmark

In this program we will be investigating different types of sorting methods
 and see which one is the fastest (most efficient). We first start with a list of
 50K elements and move our way up to 300K elements with an increment of 50K; each having
 random integers to be sorted. 
 
 Once that's created, we call each method on all the lists
 and time them using the currentTimeMills method. To prevent the system from overflowing,
 we copy our original list into another and continue. Once all is finished, we print
 them nice and neat in a table.
  


Sorting Benchmarks in milliseconds
 -
 ```
 Array Size    |  Selection  |    Merge    |    Quick    |     Heap    |   Radix
--------------------------------------------------------------------------------------
          50000|         2044|           81|           50|          211|           25|
         100000|         4385|           16|           13|           82|           21|
         150000|         3862|           32|           20|           94|           23|
         200000|         7087|           37|           28|          166|           32|
         250000|        10495|           46|           38|          300|           58|
         300000|        16047|           63|           45|          266|           38|
 ```

## Sort Algorithms

### Selection

Source [Wikipedia](https://en.wikipedia.org/wiki/Selection_sort), The algorithm divides the input list into two parts: a sorted sublist of items which is built up from left to right at the front (left) of the list and a sublist of the remaining unsorted items that occupy the rest of the list. Initially, the sorted sublist is empty and the unsorted sublist is the entire input list. The algorithm proceeds by finding the smallest (or largest, depending on sorting order) element in the unsorted sublist, exchanging (swapping) it with the leftmost unsorted element (putting it in sorted order), and moving the sublist boundaries one element to the right.

![selection sort Gif](https://upload.wikimedia.org/wikipedia/commons/9/94/Selection-Sort-Animation.gif)

#### Properties

Data structure: Array

#### Time Complexity

* Worst case performance O(n^2)
* Best case performance O(n^2)
* sAverage case performance O(n^2)



#### System requirement

* java 8 or later 


 Author - Arman Sadeghi
 
 COMSC76 - April 2020
 
