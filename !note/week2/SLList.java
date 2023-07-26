public class SLList {
  private class IntNode {
    public int item;
    public IntNode next;

    public IntNode(int item, IntNode next) {
      this.item = item;
      this.next = next;
    }
  }

  private IntNode first;

  public void addFirst(int x) {
    first = new IntNode(x, first);
  }

  public void insert(int item, int position) {
    if (position <= 0) {
      // Insert at the beginning of the list
      first = new IntNode(item, first);
    } else {
      IntNode curr = first;
      while (position > 1 && curr.next != null) {
        curr = curr.next;
        position--;
      }
      IntNode newNode = new IntNode(item, curr.next);
      curr.next = newNode;
    }
  }

  /** reverses the elements iteratively */
  public void reverse_itera() {
    IntNode frontOfReversed = null;
    IntNode nextNodeToAdd = first;
    while (nextNodeToAdd != null) {
      IntNode remainderOfOriginal = nextNodeToAdd.next;
      nextNodeToAdd.next = frontOfReversed;
      frontOfReversed = nextNodeToAdd;
      nextNodeToAdd = remainderOfOriginal;
    }
    first = frontOfReversed;
  }

  /** reverses the elements recursively */
  public void reverse_recur() {
    first = reverseRecursiveHelper(first);
  }

  private IntNode reverseRecursiveHelper(IntNode front) {
    if (front == null || front.next == null) {
      return front;
    } else {
      IntNode reversed = reverseRecursiveHelper(front.next);
      front.next.next = front;
      front.next = null;
      return reversed;
    }
  }

  // Helper method to create a string representation of the list
  public String toString() {
    StringBuilder sb = new StringBuilder();
    IntNode curr = first;
    while (curr != null) {
      sb.append(curr.item);
      if (curr.next != null) {
        sb.append(" → ");
      }
      curr = curr.next;
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    SLList list = new SLList();
    list.addFirst(2);
    list.addFirst(6);
    list.addFirst(5);

    System.out.println("Original list: " + list); // Output: 5 → 6 → 2

    list.insert(10, 1);
    System.out.println("After insert(10, 1): " + list); // Output: 5 → 10 → 6 → 2

    list.insert(10, 7);
    System.out.println("After insert(10, 7): " + list); // Output: 5 → 6 → 2 → 10

    list.reverse_recur();
    System.out.println(list);
  }
}