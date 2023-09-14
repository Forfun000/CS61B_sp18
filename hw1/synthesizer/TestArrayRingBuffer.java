package synthesizer;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the ArrayRingBuffer class.
 * 
 * @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<String> arb = new ArrayRingBuffer<>(4);
        arb.enqueue("apple");
        assertEquals(arb.peek(), "apple");
        assertEquals(arb.dequeue(), "apple");
        assertEquals(arb.capacity(), 4);
        assertEquals(arb.fillCount(), 0);
        for (int i = 0; i < arb.capacity(); i++) {
            arb.enqueue("apple");
        }
        assertEquals(arb.peek(), "apple");
        arb.dequeue();
        arb.enqueue("bell");
        assertEquals(arb.peek(), "apple");
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        assertEquals(arb.peek(), "bell");
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
}
