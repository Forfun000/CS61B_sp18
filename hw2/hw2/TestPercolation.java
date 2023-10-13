package hw2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestPercolation {
    @Test
    public void test1() {
        Percolation p = new Percolation(3);
        p.open(0, 0);
        p.open(0, 1);
        p.open(0, 2);
        assertTrue(p.percolates());
        assertEquals(p.numberOfOpenSites(), 3);
    }

    @Test
    public void test2() {
        PercolationStats stats = new PercolationStats(20, 10, new PercolationFactory());
        System.out.println(stats.mean());
    }
}
