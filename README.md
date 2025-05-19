# KnuthProblem-Solver

Code developed on the grounds of the Artificial Intelligence course in the University of Macedonia. Specifically, during this course, we were asked to develop code using search algorithms to implement a solution to Knuth's Problem which states that "any positive integer can be reached from the number 4 by applying the operations square root, factorial, and floor". This project in Java implements a solution to Knuth's Problem using two algorithms: **Breadth First Search (BFS)** and **Iterative Deepening Search (DIS)**.

## Code Explanation

The following Java code consists of two classes: Main and Node. The Node class is used to create Node objects, in order to implement the search algorithms correctly. Users are provided with a decision of what integer to find as well as what algorithm to use. The program attempts to find a solution that reaches the given integer starting from the number 4, and if it does, it lists the steps taken, as well as the time it took the algorithm to find the solution in milliseconds.
