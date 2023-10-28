package lab11.graphs;

import edu.princeton.cs.algs4.Stack;

/**
 * @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /*
     * Inherits public fields:
     * public int[] distTo;
     * public int[] edgeTo;
     * public boolean[] marked;
     */
    private boolean cycleFound = false;
    Stack<Integer> stack = new Stack<>();

    public MazeCycles(Maze m) {
        super(m);
        edgeTo[0] = 0;
        marked[0] = true;
        stack.push(maze.xyTo1D(1, 1));
    }

    @Override
    public void solve() {
        this.cycleSolver();
    }

    // Helper methods go here
    private void cycleSolver() {
        while (!stack.isEmpty() && !cycleFound) {
            int ptr = stack.pop();
            for (int w : maze.adj(ptr)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = ptr;
                    stack.push(w);
                    announce();
                    cycleSolver();
                    break;
                } else if (edgeTo[ptr] != w) {
                    cycleFound = true;
                    edgeTo[w] = ptr;
                    announce();
                    return;
                }
            }

        }
    }
}
