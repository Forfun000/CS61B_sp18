package lab11.graphs;

import org.w3c.dom.Node;

import edu.princeton.cs.algs4.MinPQ;

/**
 * @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private MinPQ<Node> minPQ = new MinPQ<>();

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        marked[s] = true;
    }

    private class Node implements Comparable<Node> {
        int position;
        int priority;

        public Node(int position) {
            this.position = position;
            this.priority = h(position);
        }

        @Override
        public int compareTo(Node node) {
            return this.priority - node.priority;
        }
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return Math.abs(maze.toX(v) - maze.toX(t)) + Math.abs(maze.toY(v) - maze.toY(t));
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        minPQ.insert(new Node(s));
        while (!targetFound) {
            Node ptrNode = minPQ.delMin();
            if (ptrNode.position == t) {
                targetFound = true;
                break;
            }
            for (int i : maze.adj(ptrNode.position)) {
                if (!marked[i]) {
                    marked[i] = true;
                    distTo[i] = distTo[ptrNode.position] + 1;
                    edgeTo[i] = ptrNode.position;
                    announce();
                    minPQ.insert(new Node(i));
                }
            }
        }

    }

    @Override
    public void solve() {
        astar(s);
    }

}
