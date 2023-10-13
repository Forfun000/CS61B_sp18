package hw2;

import java.util.ArrayList;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int N;
    private int T;
    private PercolationFactory pf;
    private int[] expResult;

    /** perform T independent experiments on an N-by-N grid */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("invalid input N/T");
        }
        this.N = N;
        this.T = T;
        this.pf = pf;
        this.expResult = new int[T];
        experiment();
    }

    private void experiment() {
        for (int i = 0; i < T; i++) {
            Percolation percolation = pf.make(N);
            int count = 0;
            ArrayList<Integer> randomNumList = new ArrayList<>(N * N);
            while (!percolation.percolates()) {
                int randomNum = StdRandom.uniform(N * N);
                if (randomNumList.contains(randomNum)) {
                    continue;
                }
                randomNumList.add(randomNum);
                count++;
                int row = randomNum % N;
                int col = randomNum / N;
                percolation.open(row, col);
            }
            expResult[i] = count;
        }
    }

    /** sample mean of percolation threshold */
    public double mean() {
        return StdStats.mean(expResult) / (N * N);
    }

    /** sample standard deviation of percolation threshold */
    public double stddev() {
        return StdStats.stddev(expResult) / (N * N);
    }

    /** low endpoint of 95% confidence interval */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    /** high endpoint of 95% confidence interval */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }
}
