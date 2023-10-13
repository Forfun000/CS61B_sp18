package hw2;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import javax.management.RuntimeErrorException;

import org.junit.Test;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    /** create N-by-N grid, with all sites initially blocked */

    private boolean[][] world;
    private int N;

    private WeightedQuickUnionUF UF;
    private int numberOfOpenSites;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N is less than 0");
        }
        this.N = N;
        this.numberOfOpenSites = 0;
        world = new boolean[N][N];
        UF = new WeightedQuickUnionUF(N * N + 2);
    }

    /** open the site (row, col) if it is not open already */
    public void open(int row, int col) {
        if (!(0 <= row && row < N) || !(0 <= col && col < N)) {
            throw new IndexOutOfBoundsException("row or col is out of bounds");
        }
        if (isOpen(row, col)) {
            return;
        }
        world[row][col] = true;
        numberOfOpenSites++;
        int[][] next = { { 1, 0 }, { 0, -1 }, { -1, 0 }, { 0, 1 } };
        for (int i = 0; i < 4; i++) {
            int x = row + next[i][0];
            int y = col + next[i][1];
            if (y < 0 || y > N - 1) {
                continue;
            }
            if (x < 0) {
                UF.union(N * N, xyTo1D(row, col));
                continue;
            }
            if (x > N - 1) {
                if (UF.connected(xyTo1D(row, col), N * N)) {
                    UF.union(N * N + 1, xyTo1D(row, col));
                }
                continue;
            }
            if (isOpen(x, y) && !UF.connected(xyTo1D(row, col), xyTo1D(x, y))) {
                UF.union(xyTo1D(row, col), xyTo1D(x, y));
            }
        }
        if (!percolates()) {
            for (int j = 0; j < N; j++) {
                if (isFull(N - 1, j)) {
                    UF.union(N * N, N * N + 1);
                }
            }
        }
    }

    /** is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        if (!(0 <= row && row < N) || !(0 <= col && col < N)) {
            throw new IndexOutOfBoundsException("row or col is out of bounds");
        }
        return world[row][col];
    }

    /** is the site (row, col) full? */
    public boolean isFull(int row, int col) {
        if (!(0 <= row && row < N) || !(0 <= col && col < N)) {
            throw new IndexOutOfBoundsException("row or col is out of bounds");
        }
        return UF.connected(xyTo1D(row, col), N * N);
    }

    /** change position to an unique integer */
    private int xyTo1D(int row, int col) {
        if (!(0 <= row && row < N) || !(0 <= col && col < N)) {
            throw new IndexOutOfBoundsException("row or col is out of bounds");
        }
        return row * N + col;
    }

    /** number of open sites */
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    /** does the system percolate? */
    public boolean percolates() {
        return UF.connected(N * N + 1, N * N);
    }

    /** use for unit testing (not required) */

    public static void main(String[] args) {
    }

}
