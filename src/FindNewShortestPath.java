public class FindNewShortestPath {

    public static void main(String[] args) {

        int exitFound = 0;

        try {
            if (args.length < 1) {
                throw new Exception("No input file specified");
            }
            String dungeonFileName = args[0];
            Dungeon newDungeon = new Dungeon(dungeonFileName);

            DLPriorityQueue<Hexagon> newPriorityQueue = new DLPriorityQueue<Hexagon>();

            newPriorityQueue.add(newDungeon.getStart(), 0);
            newDungeon.getStart().markEnqueued();

            if (newDungeon.getStart().isExit()) {
                exitFound = 1;
            }

            while ((!(newPriorityQueue.isEmpty())) && (exitFound == 0)) {

                Hexagon removedHex = newPriorityQueue.removeMin();
                removedHex.markDequeued();

                if (removedHex.isExit()) {
                    exitFound = 1;
                    continue;
                }

                int dragonFound = 0;

                for (int i = 0; i < 6; i++) {
                    int neighborDistWasChanged = 0;
                    Hexagon neighbor = removedHex.getNeighbour(i);
                    if (neighbor == null) {
                        continue;
                    }
                    if (neighbor.isDragon() || removedHex.isDragon()){
                        dragonFound = 1;
                        break;
                    }
                    if (neighbor.isWall()) {
                        continue;
                    }
                    if (neighbor.isMarkedDequeued()) {
                        continue;
                    }
                    int D = 1 + removedHex.getDistanceToStart();
                    if (neighbor.getDistanceToStart() > D) {
                        neighbor.setDistanceToStart(D);
                        neighbor.setPredecessor(removedHex);
                        neighborDistWasChanged = 1;
                    }

                    if ((neighbor.isMarkedEnqueued()) && (neighborDistWasChanged == 1)) {
                        newPriorityQueue.updatePriority(neighbor, (neighbor.getDistanceToStart() + neighbor.getDistanceToExit(newDungeon)));
                    }

                    else if (!(neighbor.isMarkedEnqueued())) {
                        newPriorityQueue.add(neighbor, (neighbor.getDistanceToStart() + neighbor.getDistanceToExit(newDungeon)));
                        neighbor.markEnqueued();
                    }
                }

                if(dragonFound == 1){
                    continue;
                }

                if(removedHex.isExit()){
                    exitFound = 1;
                }
            }

            if(newPriorityQueue.isEmpty()){
                throw new EmptyPriorityQueueException("Whether there is or isn't an exit cannot be determined because the priority queue is empty.");
            }
            else if(exitFound == 1){
                System.out.println("Path of length "+(newPriorityQueue.getRear().getDataItem().getDistanceToStart()+1)+" found");
            }
            else{
                System.out.println("No path found");
            }

        }
        catch(InvalidDungeonCharacterException e){
            System.out.println(e);
        }
        catch(EmptyPriorityQueueException e){
            System.out.println(e);
        }
        catch(InvalidElementException e){
            System.out.println(e);
        }
        catch(InvalidNeighbourIndexException e){
            System.out.println(e);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}