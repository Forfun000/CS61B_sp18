package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

/**
 * @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /*
     * Inherits public fields:
     * public int[] distTo;
     * public int[] edgeTo;
     * public boolean[] marked;
     */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Queue<Integer> searchQueue;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        marked[s] = true;
        searchQueue = new Queue<>();
        searchQueue.enqueue(s);
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        while (!targetFound && !searchQueue.isEmpty()) {
            int ptr = searchQueue.dequeue();
            if (ptr == t) {
                targetFound = true;
                break;
            }
            for (int w : maze.adj(ptr)) {
                if (!marked[w]) {
                    edgeTo[w] = ptr;
                    marked[w] = true;
                    distTo[w] = distTo[ptr] + 1;
                    searchQueue.enqueue(w);
                }
                announce();
            }
        }
        return;
    }

    @Override
    public void solve() {
        bfs();
    }
}
