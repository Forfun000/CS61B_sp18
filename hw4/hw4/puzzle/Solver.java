package hw4.puzzle;

import java.util.ArrayList;
import java.util.Stack;

import edu.princeton.cs.algs4.MinPQ;

public class Solver {

    private MinPQ<Node> minPQ = new MinPQ<>();
    private ArrayList<WorldState> solutions = new ArrayList<>();

    private class Node implements Comparable<Node> {
        WorldState worldState;
        Node parentNode;
        int moves;
        int priority;

        public Node(WorldState worldState, Node parentNode, int moves) {
            this.worldState = worldState;
            this.parentNode = parentNode;
            this.moves = moves;
            this.priority = moves + worldState.estimatedDistanceToGoal();
        }

        @Override
        public int compareTo(Node node) {
            return this.priority - node.priority;
        }
    }

    /**
     * Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     */
    public Solver(WorldState initial) {
        minPQ.insert(new Node(initial, null, 0));
        while (!minPQ.min().worldState.isGoal()) {
            Node minNode = minPQ.delMin();
            for (WorldState w : minNode.worldState.neighbors()) {
                if (minNode.parentNode == null || !w.equals(minNode.parentNode.worldState)) {
                    minPQ.insert(new Node(w, minNode, minNode.moves + 1));
                }
            }
        }
    }

    /**
     * Returns the minimum number of moves to solve the puzzle starting
     * at the initial WorldState
     */
    public int moves() {
        return minPQ.min().moves;
    }

    /**
     * Returns a sequence of WorldStates from the initial WorldState
     * to the solution.
     */
    public Iterable<WorldState> solution() {
        Stack<WorldState> stack = new Stack<>();
        Node ptrNode = minPQ.min();
        while (ptrNode != null) {
            stack.push(ptrNode.worldState);
            ptrNode = ptrNode.parentNode;
        }
        while (!stack.isEmpty()) {
            solutions.add(stack.pop());
        }
        return solutions;
    }
}