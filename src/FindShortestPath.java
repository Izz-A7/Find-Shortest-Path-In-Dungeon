import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

public class FindShortestPath {

//	// Number of neighbours will always be 6 since there are only 6 sides to a hexagon
	private final static int NUM_NEIGHBOURS = 6;

    public static void main(String[] args) {
        try {
        	// If command line was not provided, throw exception
            if (args.length < 1) throw new Exception("No input file specified");
            // Get dungeonFileName arguments
            String dungeonFileName = args[0];
            
            // Call upon private helper method to see if the inputed file is valid.
            if (!isValidInputFile(dungeonFileName))
                throw new FileNotFoundException("Input file was not found or it isn't readable.");
            
            // Create new dungeon object using the inputed file name
            Dungeon dungeon = new Dungeon(dungeonFileName);
            // Get the starting chamber
            Hexagon startingChamber = dungeon.getStart();
            
            // Initialize priority queue, add the start chamber and set its priority to zero
            DLPriorityQueue<Hexagon> prioQueue = new DLPriorityQueue<>();
            startingChamber.setDistanceToStart(0);
            prioQueue.add(startingChamber, 0);
            startingChamber.markEnqueued();
            
            // Create current and exit variables
            Hexagon currChamber;
            Hexagon exitChamber = null;
            
            // NOTE: Instead of doing what the pseuducode said to do I made the code break if the exit was found instead of using a while loop
            // the priority queue isn't false continue
            while (prioQueue.isEmpty() == false) {
            	// Remove smallest priority chamber and mark it as dequeued
            	currChamber = prioQueue.removeMin();
            	currChamber.markDequeued();
                
                // If the current chamber is the exit, break
                if (currChamber.isExit()) {
                    exitChamber = currChamber;
                    break;
                }
                
                // If the current chamber has a dragon or the neighbouring chamber has a dragon, continue
                if (currChamber.isDragon() || hasNeighbouringDragon(currChamber)) {
                    continue;
                }
                
                // Iterating through neighbouring chambers
                for (int i = 0; i < NUM_NEIGHBOURS; i++) {
                    try {
                    	// Getting neighbouring chamber at the current index
                        Hexagon neighbour = currChamber.getNeighbour(i);
                        
                        // Check if neighbour does not equal null, not marked dequeued, and is not blocked
                        if (neighbour != null && !neighbour.isMarkedDequeued() && !isPathBlocked(neighbour)) {
                        	if (!isHexagonValid(neighbour, dungeon)) {
                        		continue;
                        	}
                        	
                        	// Calculate/update distance
                            int updatedDistance = currChamber.getDistanceToStart() + 1;
                            
                            // Call on private helper method
                            updateHexagon(currChamber, neighbour, prioQueue, dungeon);
                        }
                    } 
                    
                    // Catch InvalidNeighbourIndexException
                    catch (InvalidNeighbourIndexException e) {
                    }
                }
            }
            
            // Call helper method to print path length
            printPathLength(exitChamber);
        } 
        
        // Catch numerous exceptions
        catch (InvalidDungeonCharacterException e) {
            System.out.println("Invalid character in dungeon file: " + e.getMessage());
        } 
        
        // Although we already called the isValidInputFile method, an error may occur during the execution
        catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        } 
        
        // Although we already called the isValidInputFile method, an error may occur during the execution
        catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
        } 
        
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    
    // Helper method to determine if there is a neighbouring dragon
    private static boolean hasNeighbouringDragon(Hexagon chamber) {
    	// Iterating through to find if the neighbour has a dragon
        for (int i = 0; i < NUM_NEIGHBOURS; i++) {
            try {
                Hexagon neighbour = chamber.getNeighbour(i);
                
                // If neighbour does not equal null and has a dragon return true
                if (neighbour != null && neighbour.isDragon()) {
                    return true;
                }
            } 
            
            // Catch exception
            catch (InvalidNeighbourIndexException e) {
            	
            }
        }
        
        return false; // Return false if no dragons were found
    }
    

    // Private helper method to see if path is blocked
    private static boolean isPathBlocked(Hexagon chamber) {
        return chamber.isWall() || chamber.isDragon();
    }

    
    // Private helper method to see if hexagon is valid (not null, more than zero, etc)
    private static boolean isHexagonValid(Hexagon chamber, Dungeon dungeon) {
        return chamber != null && chamber.getDistanceToStart() >= 0 && chamber.getDistanceToExit(dungeon) >= 0;
    }
    
    
    // Private helper method to see if the file that was inputed is valid
    private static boolean isValidInputFile(String fileName) {
        File file = new File(fileName);
        return file.exists() && file.isFile() && file.canRead();
    }

    
    // Private helper method to calculate/update distance, set predecessor and update priority if we need to
    private static void updateHexagon(Hexagon currentChamber, Hexagon neighbour, DLPriorityQueue<Hexagon> prioQueue, Dungeon dungeon) {
        // Calculate/update distance
        int updatedDistance = currentChamber.getDistanceToStart() + 1;

        // Check if the updated distance is smaller than the distance to start
        if (updatedDistance < neighbour.getDistanceToStart()) {
            // Updating variables
            neighbour.setDistanceToStart(updatedDistance);
            neighbour.setPredecessor(currentChamber);

            // Initialize and calculate new priority
            double newPriority;
            newPriority = updatedDistance + neighbour.getDistanceToExit(dungeon);

            // If the neighbour is marked enqueued, update the priority
            if (neighbour.isMarkedEnqueued()) {
                prioQueue.updatePriority(neighbour, newPriority);
            }
            
            // Else, add it to the queue and mark it as enqueued
            else {
                prioQueue.add(neighbour, newPriority);
                neighbour.markEnqueued();
            }
        }
    }
    
    
    // Private helper method to print path length
    private static void printPathLength(Hexagon exitChamber) {
    	// Initialize path length
    	int pathLength;
    	
    	// If exit chamber is null print no path was found
    	if (exitChamber == null) {
            System.out.println("No path was found");
    	}
    	
    	// Else, print the path length
    	else {
        	pathLength = exitChamber.getDistanceToStart() + 1;
            System.out.println("Path of length " + pathLength + " found");

    	}
    }
}





