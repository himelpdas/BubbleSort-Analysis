##Bubble Sort Algorithm
	
>BubbleSort is one of the simplest sorting algorithms. The sorting algorithm works by iteratively or recursively stepping through a collection and then comparing pairs of adjacent elements. If the pair of elements are not in order, the algorithm commits a swap. The algorithm repeats this process until no more swaps occur, indicating the algorithm has completed. In the worst case, BubbleSort will run on a collection that is sorted in reverse order. This means that the first iteration will step through n elements. An interesting property of BubbeSort is that after each iteration (i), the (n - i)th index will be guaranteed sorted, as well as anything after that position. So for example, in the first iteration, the (n-1)th index will be sorted; in the second iteration, the (n-2)th index and (n-1)th will be guaranteed sorted, etc. This means in the first iteration, the algorithm would have to visit n elements, and then n -  1 elements, and then n – 1 elements, and so on. The number of comparisons can be calculated as n + n -1 + n – 2 + n -3 … + 1. This equals n*(n + 1))/2 or big-O(n2) comparisons.

>I created a Swing based application that tests both the recursive and iterative versions of BubbleSort. The critical point chosen for both iterative and recursive algorithms is  the point in the source code where a comparison is made, as shown in the comments of the BubbleSort.java class. The program outputs a table via Java Swing’s JTable as shown below. The program has only 1 dependency which is the Apache Commons Mathematics 2.2 library. This library is used to conveniently calculate the mean and standard deviation from a series of numerical values. The values of n used are 10, 25, 50, 100, 250, 500, 1000, 2500, 5000 and 10000. After this, the program may hang indefinitely on a Intel Core i7 machine. However, it is enough to demonstrate the O(n2) nature of both the recursive and iterative BubbleSort algorithms.

!()[img/output.png]

>The average critical operation count of the iterative and the recursive method is nearly identical, with the iterative method being negligibly more efficient than the recursive. This comes to no surprise as the iterative and the recursive algorithms are simply two approaches to solving the same problem.

!()[img/chart1.png]

>Standard deviation is a measure of how varied a set of values are from the mean. The standard deviation of count for the recursive version is zero. This shows that during the runs, the comparison counts remained constant regardless of the position of the elements in the lists. My theory is that if a random list is already nearly sorted, the iterative version will have to do relatively fewer comparisons, and the while loop can exit a little sooner from the Boolean variable swap remaining false (no swap means the list is sorted). Meanwhile the recursive version has to wait until the base case is reached, that is n==1, no matter if the list is nearly sorted or not.	 

!()[img/chart2.png]
 
>As expected, the average execution time reflects similar performances to the average critical operations, with the iterative version fairing a little bit better than the recursive version.

!()[img/chart3.png]

>The graphs of the iterative and recursive versions for the standard deviation of execution times varied enough where it didn’t need a 3D graph. As expected the iterative version clearly has more “variance” when it comes to execution times. This is because I suspect that the iterative version is allowed to exit the while loop earlier in random cases where the list is relatively more sorted. The recursive version on the other hand seems more “stable” as the slope after n=5000 is smaller than the iterative.	 

!()[img/chart4.png]

>As mentioned before, I calculated that the Big-O is essentially n2 from the calculation of n*(n + 1))/2. To see if the running times yielded from the recursive and iterative programs are similar to the theoretical n*(n + 1))/2 running time, I added another “calculated” column to the Average Critical Operations chart. I chose to use the calculated formula instead of simply Big-O(n2) in hopes of getting a result closer to the program’s generated results (though either will show quadratic growth). In the end I was extremely pleased with the results, where the real world results matches nearly exactly to the theoretical result.
 	 
!()[img/chart5.png]

>The goal of this was to help me more deeply learn one of the most overlooked–but in my opinion–important sorting algorithms. I find BubbleSort to be a very intuitive algorithm for sorting a collection. I learned that though the recursive and iterative versions are solving the same problem, they have some slight differences that should not be overlooked. Overall, this project got me excited to understand the importance of measuring different statistical variables when analyzing an algorithm. By looking at the standard deviation, one can grasp how “consistently” the algorithm is performed behind the scenes. I hope to analyze more algorithms like this in the future.