package hw2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestPercolation {
    @Test
    public void test1() {
        Percolation p = new Percolation(2);
        p.open(1, 0);
        p.open(0, 0);
        assertTrue(p.percolates());
        assertEquals(p.numberOfOpenSites(), 2);
    }

    @Test
    public void test2() {
        PercolationStats stats = new PercolationStats(20, 10, new PercolationFactory());
        System.out.println(stats.mean());
    }
}
