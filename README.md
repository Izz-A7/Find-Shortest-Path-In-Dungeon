# Find-Shortest-Path-In-Dungeon
This project implements a program that calculates the shortest path from the starting chamber to the exit chamber in a dungeon. The program takes into account blocked paths and nearby dragons to find the most efficient path. The program takes an input file containing the layout of the dungeon and uses an algorithm to find the shortest path.

The implementation is done using Java and the provided files include:
- '**Dungeon.java**', '**EmptyPriorityQueueException.java**', '**Hexagon.java**', '**HexColors.java**', '**HexComponent.java**', '**HexLayout.java**', '**InvalidDungeonCharacterException.java**', '**InvalidElementException.java**', '**InvalidNeighbourIndexException.java**', '**PriorityQueueADT.java**', '**DLinkedNode.java**', and '**DLPriorityQueue.java**'.

## Skills Learned
- Implementing priority queues using doubly-linked lists.
- Implementing an algorithm to find shortest path.
- Exception handling and error messages in Java.
- Handling file input and output, including reading in and checking for file validity.
- Object-oriented programming, using classes such as Dungeon, Hexagon, and DLPriorityQueue to create a functioning program.
- Implementing helper methods to check for various conditions, such as whether a path is blocked or a Hexagon object is valid.

## Usage
- Clone the repository to your local machine
- Navigate to the project directory
- Compile the project using javac *.java
- Run the program using java FindShortestPath inputfile.txt
- The program takes one argument, which is the input file containing the dungeon layout. The output will be the shortest path length from the starting chamber to the exit chamber.

## Constants
- NUM_NEIGHBOURS: The number of neighbors a hexagon can have (6 in this case)

## Functions

DLinkedNode<T>
- '**DLinkedNode (T data, double prio)**': Constructs a new node with the given data and priority
- '**DLinkedNode()**': Constructs a new node with null data and 0 priority
- '**double getPriority()**': Returns the priority value of the node
- '**T getDataItem()**': Returns the data item of the node
- '**DLinkedNode<T> getNext()**': Returns the next node
- '**DLinkedNode<T> getPrev()**': Returns the previous node
- '**setDataItem(T data)**': Sets the data item of the node
- '**setNext(DLinkedNode<T> node)**': Sets the next node of the current node
- '**setPrev(DLinkedNode<T> node)**': Sets the previous node of the current node
- '**setPriority(double prio)**': Sets the value of the priority of the node

DLPriorityQueue<T>
- '**DLPriorityQueue()**': Constructs a new empty priority queue
- '**add (T dataItem, double priority)**': Adds a new node with the given data and priority to the queue
- '**updatePriority (T dataItem, double newPriority) throws InvalidElementException**': Updates the priority of a node in the queue
- '**T removeMin() throws EmptyPriorityQueueException**': Removes and returns the data with the smallest priority from the queue
- '**isEmpty()**': Returns true if the priority queue is empty and false otherwise
- '**int size()**': Returns the size of the queue
- '**String toString()**': Returns a string representation of the priority queue
- '**DLinkedNode<T> getRear()**': Returns the rear/end of the queue

FindShortestPath
- '**main**': The main method that runs the program and finds the shortest path
- '**hasNeighbouringDragon(Hexagon chamber)**': Helper method to determine if there is a neighboring dragon
- '**isPathBlocked(Hexagon chamber)**': Private helper method to see if path is blocked
- '**isHexagonValid(Hexagon chamber, Dungeon dungeon)**': Private helper method to see if the Hexagon object is valid (not null, more than zero, etc).
- '**isValidInputFile(String fileName)**': Private helper method to see if the file that was inputted is valid.
- '**updateHexagon(Hexagon currentChamber, Hexagon neighbour, DLPriorityQueue<Hexagon> prioQueue, Dungeon dungeon)**': Private helper method to calculate/update distance, set predecessor, and update priority if we need to.
- '**printPathLength(Hexagon exitChamber)**': Private helper method to print the path length.

## Limitations
- The program does not take into account dynamic changes to the game board, such as moving obstacles or changing terrain.

## Possible Improvements
- Implement different algorithms
- Enhance the user interface

## Conclusion
Overall, this project provided an opportunity to apply my knowledge of data structures and algorithms to solve a practical problem. By implementing a priority queue and using it to find the shortest path in a dungeon, I gained a better understanding of how priority queues can be used to efficiently process data. Additionally, the project provided an opportunity to practice error handling and code organization, as well as to learn about hexagonal grids and their applications.
