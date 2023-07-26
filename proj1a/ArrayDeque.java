/**
 * Array based list.
 */

// 0 1 2 3 4 5 6 7
// items: [6 9 -1 2 0 0 0 0 ...]
// size: 5

/*
 * Invariants:
 * addLast: The next item we want to add, will go into position size
 * getLast: The item we want to return is in position size - 1
 * size: The number of items in the list should be size.
 */

public class ArrayDeque<T> {
  final int initCapacity = 8;
  private T[] items;
  private int size;
  private int capacity;

  /** Creates an empty list. */
  public ArrayDeque() {
    items = (T[]) new Object[initCapacity];
    size = 0;
    capacity = initCapacity;
  }

  /** Creates a deep copy of other */
  public ArrayDeque(ArrayDeque other) {
    this.size = other.size;
    this.capacity = other.capacity;
    items = (T[]) new Object[capacity];
    T[] originalItems = (T[]) other.items;
    for (int i = 0; i < size - 1; i++) {
      items[i] = originalItems[i];
    }
  }

  /** update the capacity of the Deque */
  private void updateCapacity(int newCapacity) {
    T[] newItems = (T[]) new Object[newCapacity];
    for (int i = 0; i < size; i++) {
      newItems[i] = items[i];
    }
    capacity = newCapacity;
    items = newItems;
  }

  /** Insert a new element at position i in the linear table */
  public void Insert(int i, T item) {
    if (size == capacity) {
      updateCapacity(size * 2);
    }
    for (int j = size; j > i; j--) {
      items[j] = items[j - 1];
    }
    items[i] = item;
    size++;
  }

  /** Adds an item of type T to the front of the deque. */
  public void addFirst(T item) {
    Insert(0, item);
  }

  /** Adds an item of type T to the back of the deque. */
  public void addLast(T item) {
    Insert(size, item);
  }

  /** Returns true if deque is empty, false otherwise. */
  public boolean isEmpty() {
    return size == 0;
  }

  /** Returns the number of items in the deque. */
  public int size() {
    return size;
  }

  /** delete an element at position i in the linear table */
  public void delete(int i) {
    for (int j = i; j < size - 1; j++) {
      items[j] = items[j + 1];
    }
    size--;
    if (size > initCapacity && size <= capacity / 4) {
      updateCapacity(size / 2);
    }
  }

  /** Gets the item at the given index */
  public T get(int index) {
    if (index < 0 || index > size - 1) {
      return null;
    }
    return items[index];
  }

  /**
   * Removes and returns the item at the front of the deque. If no such item
   * exists, returns null.
   */
  public T removeFirst() {
    if (isEmpty()) {
      return null;
    }
    T ans = get(0);
    delete(0);
    return ans;
  }

  /** Removes and returns the item at the back of the deque */
  public T removeLast() {
    if (isEmpty()) {
      return null;
    }
    T ans = get(size - 1);
    delete(size - 1);
    return ans;
  }

  /**
   * Prints the items in the deque from first to last, separated by a space.
   * Once all the items have been printed, print out a new line.
   */
  public void printDeque() {
    String ans = "";
    for (int i = 0; i < size; i++) {
      ans += items[i].toString() + " ";
    }
    System.out.println(ans);
    System.out.println();
  }
}