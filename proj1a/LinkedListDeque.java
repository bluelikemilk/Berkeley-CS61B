/** Build a generic class for double ended queue (DLList) using circular sentinel*/
public class LinkedListDeque<T> {

    // Instance variables
    private TNode sentinel;
    private int size;

    // private classes
    /** Build a private class for each node of the DLList */
    private class TNode {

        public TNode prev;
        public T item;
        public TNode next;
        /** Constructor*/
        public TNode(TNode p, T i, TNode n) {

            prev = p;
            item = i;
            next = n;
        }
    }

    // public classes
    /** Null constructor: Create a null LinkedListDeque*/
    public LinkedListDeque() {

        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

//    /** Copy constructor: Create a deep copy of other LinkedListDeque*/
//    public LinkedListDeque(LinkedListDeque other) {
//
//        sentinel = new TNode(null, null, null);
//        sentinel.next = sentinel;
//        sentinel.prev = sentinel;
//
//        TNode p = other.sentinel.next; // p points to the first element of other
//        while (p != other.sentinel) { // when p = other.sentinel, end loop
//            addLast(p.item);
//            p = p.next;
//        }
//
//    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {

        // sentinel.next always points to the first element
        sentinel.next = new TNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;

        size = size + 1;
    }

    /** Adds an item of type T to the back of the deque.*/
    public void addLast(T item) {

        TNode p = sentinel.prev; // p points to the last element
        p.next = new TNode(p, item, sentinel);
        sentinel.prev = p.next;
        size = size + 1;
    }

    /** Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty() {

        if (size == 0) {
            return true;
        }
        return false;

    }

    /** Returns the number of items in the deque.*/
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.*/
    public void printDeque() {

        TNode p = sentinel;
        for (int i = 0; i < size; i++) {
            p = p.next;
            System.out.print(p.item + " ");
        }
        System.out.println('\n');
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.*/
    public T removeFirst() {
        if(isEmpty()) {
            System.out.println("The list is empty!");
            return null;
        }
        TNode p = sentinel.next; // p points to the first element
        if (p == null) {
            return null;
        } else {
            sentinel.next = p.next; // update the first item
            p.next.prev = sentinel; // connect new first item with sentinel
            size = size - 1;
            p.next = null;
            p.prev = null;
            return p.item;
        }
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.*/
    public T removeLast() {
        if(isEmpty()) {
            System.out.println("The list is empty!");
            return null;
        }
        TNode p = sentinel.prev; // p points to the last element
        if (p == null) {
            return null;
        } else {
            sentinel.prev = p.prev; // update the last item
            p.prev.next = sentinel; // connect new last item with sentinel
            size = size - 1;
            p.next = null;
            p.prev = null;
            return p.item;
        }
    }

    /** Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth. If no such item exists, returns null.
     * Must not alter the deque!*/
    public T get(int index) {

        TNode p = sentinel;
        if (index > size - 1 || index < 0) { // index exceeds boundary
            return null;
        } else {
            for (int i = 0; i < index + 1; i++) {
                p = p.next;
            }
            return p.item;
        }
    }

    private TNode get(TNode curr, int index) {
        if (index == 0) {
            return curr;
        } else {
            return get(curr.next, index - 1);
        }
    }

    /** Same as get() but recursively*/
    public T getRecursive(int index) {

        TNode p = get(sentinel.next, index);
        return p.item;

    }

}
