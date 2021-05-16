import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstSearch {

    private static final int MAX = Integer.MAX_VALUE;
    private boolean[] visited;
    private int[] et;
    private int[] dt;

    public BreadthFirstSearch(Graph G, int s){
        visited = new boolean[G.V()];
        dt = new int[G.V()];
        et = new int[G.V()];
        for(int v = 0; v < G.V(); v++)
            dt[v] = MAX;
        bfs(G, s);
    }

    // BFS which implements Dijkstra's algorithm
    private void bfs(Graph G, int s){
        Queue<Integer> q = new LinkedList<>();
        visited[s] = true;
        dt[s] = 0;
        q.add(s);

        while(!q.isEmpty()){
            int v = q.remove();
            for(int w : G.adj(v)){
                if(!visited[w]){
                    et[w] = v;
                    dt[w] = dt[v] + 1;
                    visited[w] = true;
                    q.add(w);
                }
            }
        }
    }

    // Returns true if there is a path, false otherwise
    public boolean hasPathTo(int v){
        return visited[v];
    }

    // Returns the distance to the verticie
    public int dt(int v){
        return dt[v];
    }

    // Iterates through the map to find a path to the location
    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v))
            return null;
        Stack<Integer> path = new Stack<>();

        int x;
        for(x = v; dt[x] != 0; x = et[x])
            path.push(x);
        path.push(x);
        return path;
    }

}
