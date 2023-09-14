package synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {

    /* return size of the buffer */
    public int capacity();

    /* return number of the items currently in the buffer */
    public int fillCount();

    /* add item x to the end */
    public void enqueue(T x);

    /* delete and return item from the front */
    public T dequeue();

    /* return (but do not delete) item from thr front */
    public T peek();

    /* is the buffer empty (fillCount equals zero)? */
    default public boolean isEmpty() {
        return fillCount() == 0;
    }

    /* is the buffer full (fillCount is same as capacity)? */
    default public boolean isFull() {
        return capacity() == fillCount();
    }

    abstract public Iterator<T> iterator();
}