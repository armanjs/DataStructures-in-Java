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

#### System requirement

* java 8 or later 


 Author - Arman Sadeghi
 
 COMSC76 - April 2020
 