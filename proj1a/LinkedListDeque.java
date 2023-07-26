public class LinkedListDeque<T> {
  private TNode sentinel;
  private int size;

  private class TNode {
    public T item;
    public TNode next;
    public TNode prev;

    public TNode(T i, TNode p, TNode n) {
      item = i;
      prev = p;
      next = n;
    }

    public TNode() {
      item = null;
    }
  }

  /** Creates an empty LinkedListDeque */
  public LinkedListDeque() {
    sentinel = new TNode();
    sentinel.next = sentinel;
    sentinel.prev = sentinel;
    size = 0;
  }

  // /** Creates a LinkedListDeque with a Node */
  // public LinkedListDeque(T item) {
  // sentinel = new TNode();
  // sentinel.next = sentinel;
  // sentinel.prev = sentinel;
  // sentinel.next = new TNode(item, sentinel, sentinel);
  // sentinel.prev = sentinel.next;
  // size = 1;
  // }

  // /** Creating an entirely new copy of other */
  // public LinkedListDeque(LinkedListDeque<T> other) {
  // sentinel = new TNode();
  // sentinel.next = sentinel;
  // sentinel.prev = sentinel;
  // size = 0;
  // for (int i = 0; i < other.size; i++) {
  // addLast(other.get(i));
  // }
  // }

  /** Adds an item of type T to the front of the deque. */
  public void addFirst(T item) {
    TNode newTNode = new TNode(item, sentinel, sentinel.next);
    sentinel.next.prev = newTNode;
    sentinel.next = newTNode;
    size = size + 1;
  }

  /** Adds an item of type T to the back of the deque. */
  public void addLast(T item) {
    TNode newTNode = new TNode(item, sentinel.prev, sentinel);
    sentinel.prev.next = newTNode;
    sentinel.prev = newTNode;
    size = size + 1;
  }

  /** Returns true if deque is empty, false otherwise. */
  public boolean isEmpty() {
    return size == 0;
  }

  /** Returns the number of items in the deque. */
  public int size() {
    return size;
  }

  /**
   * Prints the items in the deque from first to last, separated by a space.
   * Once all the items have been printed, print out a new line.
   */
  public void printDeque() {
    TNode ptr = sentinel.next;
    for (int i = 0; i < size; i++) {
      System.out.print(ptr.item + " ");
      ptr = ptr.next;
    }
    System.out.println();
  }

  /**
   * Removes and returns the item at the front of the deque. If no such item
   * exists, returns null.
   */
  public T removeFirst() {
    if (isEmpty()) {
      return null;
    }
    size = size - 1;
    TNode delNode = sentinel.next;
    sentinel.next = delNode.next;
    delNode.next.prev = delNode.prev;
    return delNode.item;
  }

  /**
   * Removes and returns the item at the back of the deque. If no such item
   * exists, returns null.
   */
  public T removeLast() {
    if (isEmpty()) {
      return null;
    }
    size = size - 1;
    TNode delNode = sentinel.prev;
    sentinel.prev = delNode.prev;
    delNode.prev.next = delNode.next;
    return delNode.item;
  }

  /**
   * Gets the item at the given index, where 0 is the front, 1 is the next
   * item,and so forth.
   */
  public T get(int index) {
    if (isEmpty() || index < 0 || index >= size) {
      return null;
    }
    TNode ptr = sentinel;
    for (int i = 0; i < index; i++) {
      ptr = ptr.next;
    }
    return ptr.next.item;
  }

  /** Same as get, but uses recursion. */
  public T getRecursive(int index) {
    if (index < 0 || index >= size) {
      return null;
    }
    return getHelper(index, sentinel.next);
  }

  private T getHelper(int index, TNode items) {
    if (index == 0) {
      return items.item;
    }
    return getHelper(index - 1, items.next);
  }
}
