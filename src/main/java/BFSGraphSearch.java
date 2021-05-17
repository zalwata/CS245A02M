import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFSGraphSearch {

    private static final int FINALVAL = Integer.MAX_VALUE;
    private boolean[] vertexVisited;
    private int[] vertexArr;
    private int[] edgeArr;

    public boolean isValidPath(int indexNum){
        return vertexVisited[indexNum];
    }


    //bfsGraph constructor
    public BFSGraphSearch(GraphDataStructure graphDataStructureObject, int indexVal){
        vertexVisited = new boolean[graphDataStructureObject.createVertex()];
        edgeArr = new int[graphDataStructureObject.createVertex()];
        vertexArr = new int[graphDataStructureObject.createVertex()];
        for(int i = 0; i < graphDataStructureObject.createVertex(); i++)
            edgeArr[i] = FINALVAL;
        combinedDijkstraAndBFS(graphDataStructureObject, indexVal);
    }

    //use dijkstra along with BFS
    private void combinedDijkstraAndBFS(GraphDataStructure ggraphObject, int indexVal){
        Queue<Integer> queueObject = new LinkedList<>();
        vertexVisited[indexVal] = true;
        edgeArr[indexVal] = 0;
        queueObject.add(indexVal);

        while(!queueObject.isEmpty()){
            int removedVal = queueObject.remove();
            for(int index : ggraphObject.adjacentIterable(removedVal)){
                if(!vertexVisited[index]){
                    vertexArr[index] = removedVal;
                    edgeArr[index] = edgeArr[removedVal] + 1;
                    vertexVisited[index] = true;
                    queueObject.add(index);
                }
            }
        }
    }

    public int initDistanceCalculation(int indexNum){
        return edgeArr[indexNum];
    }

    //return the iterable to return the path
    public Iterable<Integer> grabThePath(int pathNum){
        if(!isValidPath(pathNum))
            return null;
        Stack<Integer> stackablePath = new Stack<>();

        int pathIndex;
        for( pathIndex = pathNum; edgeArr[pathIndex] != 0; pathIndex = vertexArr[pathIndex])
            stackablePath.push(pathIndex);
        stackablePath.push(pathIndex);
        return stackablePath;
    }

}
