import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void arrayDequeTest() {
        StudentArrayDeque<Integer> stuArraydeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solArraydeque = new ArrayDequeSolution<>();
        String operationConsequence = "";
        for (int i = 0; i < 100; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.25) {
                stuArraydeque.addFirst(i);
                solArraydeque.addFirst(i);
                operationConsequence += "addFirst(" + i + ")\n";
                assertEquals(operationConsequence,
                        stuArraydeque.get(0),
                        solArraydeque.get(0));
            } else if (numberBetweenZeroAndOne < 0.5
                    && numberBetweenZeroAndOne >= 0.25) {
                stuArraydeque.addLast(i);
                solArraydeque.addLast(i);
                operationConsequence += "addLast(" + i + ")\n";
                assertEquals(operationConsequence,
                        stuArraydeque.get(stuArraydeque.size() - 1),
                        solArraydeque.get(solArraydeque.size() - 1));
            } else if (numberBetweenZeroAndOne < 0.75
                    && numberBetweenZeroAndOne >= 0.5
                    && solArraydeque.size() > 0) {
                operationConsequence += "removeFirst(): " + solArraydeque.get(0) + "\n";
                assertEquals(operationConsequence,
                        stuArraydeque.removeFirst(),
                        solArraydeque.removeFirst());
            } else if (numberBetweenZeroAndOne >= 0.75
                    && solArraydeque.size() > 0) {
                operationConsequence += "removeLast(): " + solArraydeque.getLast() + "\n";
                assertEquals(operationConsequence,
                        stuArraydeque.removeLast(),
                        solArraydeque.removeLast());
            }
        }
    }
}
