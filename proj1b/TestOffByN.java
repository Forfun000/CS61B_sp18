import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestOffByN {

    @Test
    public void offByNTest() {
        OffByN offBy5 = new OffByN(5);
        assertTrue(offBy5.equalChars('a', 'f'));
        assertTrue(offBy5.equalChars('f', 'a'));
        assertFalse(offBy5.equalChars('a', 'c'));
        assertFalse(offBy5.equalChars('a', 'a'));
        assertFalse(offBy5.equalChars('r', 'a'));
        assertFalse(offBy5.equalChars('l', 'k'));
        assertFalse(offBy5.equalChars('d', '4'));
    }
}
