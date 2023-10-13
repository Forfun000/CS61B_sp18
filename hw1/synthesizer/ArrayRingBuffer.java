// Make sure to make this class a part of the synthesizer package
// package <package name>;
package synthesizer;

import java.util.Iterator;

import javax.management.RuntimeErrorException;

// Make sure to make this class and all of its methods public
// Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first; // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // Create new array with capacity elements.
        // first, last, and fillCount should all be set to 0.
        // this.capacity should be set appropriately. Note that the local variable
        // here shadows the field we inherit from AbstractBoundedQueue, so
        // you'll need to use this.capacity to set the capacity.
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // Enqueue the item. Don't forget to increase fillCount and update last.
        if (fillCount >= capacity) {
            throw new RuntimeErrorException(null, "Ring buffer overflow");
        }

        if (last == capacity) {
            last = 0;
            rb[last] = x;
        } else {
            rb[last] = x;
        }
        last++;
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // Dequeue the first item. Don't forget to decrease fillCount and update
        if (fillCount == 0) {
            throw new RuntimeErrorException(null, "Ring buffer underflow");
        }
        T result = rb[first];
        rb[first] = null;
        if (first == capacity - 1) {
            first = 0;
        } else {
            first++;
        }
        fillCount--;
        return result;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // Return the first item. None of your instance variables should change.
        if (fillCount == 0) {
            throw new RuntimeErrorException(null, "Ring buffer underflow");
        }
        return rb[first];
    }

    // When you get to part 5, implement the needed code to support iteration.
    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return new KeyIterator();
    }

    private class KeyIterator implements Iterator<T> {
        private int key;
        private int count;

        public KeyIterator() {
            key = first;
            count = 0;
        }

        @Override
        public T next() {
            // TODO Auto-generated method stub
            T res = rb[key];
            key++;
            count++;
            if (key == capacity()) {
                key = 0;
            }
            return res;
        }

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return count < fillCount();
        }

    }

}
