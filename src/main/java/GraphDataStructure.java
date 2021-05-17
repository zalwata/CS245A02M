public class GraphDataStructure {

    private final int finalVerticeNum;
    private int globalCounter;
    private BagStructure<Integer>[] adjacentArr;

    public GraphDataStructure(int finalVerticeNum) {
        if (finalVerticeNum < 0) throw new IllegalArgumentException("vertices > 0");
        this.finalVerticeNum = finalVerticeNum;
        this.globalCounter = 0;
        adjacentArr = (BagStructure<Integer>[]) new BagStructure[finalVerticeNum];
        for (int i = 0; i < finalVerticeNum; i++) {
            adjacentArr[i] = new BagStructure<Integer>();
        }
    }

    public void initiateToAddEdge(int verticeIndexNum, int edgeIndexNum) {
        if (verticeIndexNum < 0 || verticeIndexNum >= finalVerticeNum) throw new IndexOutOfBoundsException();
        if (edgeIndexNum < 0 || edgeIndexNum >= finalVerticeNum) throw new IndexOutOfBoundsException();
        globalCounter++;
        adjacentArr[verticeIndexNum].add(edgeIndexNum);
        adjacentArr[edgeIndexNum].add(verticeIndexNum);
    }

    public int createVertex() {
        return finalVerticeNum;
    }

    public Iterable<Integer> adjacentIterable(int vertexIndexNum) {
        if (vertexIndexNum < 0 || vertexIndexNum >= finalVerticeNum) throw new IndexOutOfBoundsException();
        return adjacentArr[vertexIndexNum];
    }
}
