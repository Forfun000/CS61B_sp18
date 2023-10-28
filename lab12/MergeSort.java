import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest
     * item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param q1 A Queue in sorted order from least to greatest.
     * @param q2 A Queue in sorted order from least to greatest.
     * @return The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>> makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item>> queue = new Queue<>();
        for (Item item : items) {
            Queue<Item> enQueue = new Queue<>();
            enQueue.enqueue(item);
            queue.enqueue(enQueue);
        }
        return queue;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and
     * q2. After
     * running this method, q1 and q2 will be empty, and all of their items will be
     * in the
     * returned queue.
     *
     * @param q1 A Queue in sorted order from least to greatest.
     * @param q2 A Queue in sorted order from least to greatest.
     * @return A Queue containing all of the q1 and q2 in sorted order, from least
     *         to
     *         greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1 == null) {
            return q2;
        } else if (q2 == null) {
            return q1;
        }
        Queue<Item> sortedQueue = new Queue<>();
        while (!q1.isEmpty() || !q2.isEmpty()) {
            sortedQueue.enqueue(MergeSort.getMin(q1, q2));
        }
        return sortedQueue;
    }

    /**
     * Returns a Queue that contains the given items sorted from least to greatest.
     */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        Queue<Queue<Item>> tmpQueue = makeSingleItemQueues(items);
        while (tmpQueue.size() != 1) {
            Queue<Queue<Item>> ttmpQueue = new Queue<>();
            while (!tmpQueue.isEmpty()) {
                Queue<Item> t1 = tmpQueue.dequeue();
                Queue<Item> t2 = tmpQueue.isEmpty() ? new Queue<>() : tmpQueue.dequeue();
                ttmpQueue.enqueue(mergeSortedQueues(t1, t2));
            }
            tmpQueue = ttmpQueue;

        }
        return tmpQueue.dequeue();
    }

    public static void main(String[] args) {
        Queue<String> students = new Queue<>();
        students.enqueue("Alice");
        students.enqueue("Vanessa");
        students.enqueue("Ethan");
        System.out.println(students);
        // System.out.println(MergeSort.makeSingleItemQueues(students));
        // Queue<String> stQueue = new Queue<>();
        // stQueue.enqueue("B");
        // System.out.println(MergeSort.mergeSortedQueues(students, stQueue));
        System.out.println(mergeSort(students));
        System.out.println(students);
    }
}
