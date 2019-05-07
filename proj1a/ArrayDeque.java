public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int RFACTOR = 2;

    /** Create an empty array deque*/
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast=1;
    }

    /** Resize the length of items array*/
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int total = items.length;
        if (nextFirst < nextLast-1) { // if start from 0, just copy the whole array
            System.arraycopy(items, nextFirst+1, a, 1, size);
        } else { // else, copy by two segments
            System.arraycopy(items, (nextFirst + 1) % total, a, 1, total - nextFirst - 1);
            System.arraycopy(items, 0, a, total - nextFirst, size - (total - nextFirst - 1));
        }
        items = a;
        nextFirst = 0;
        nextLast = size + 1;
    }

    /** Add an item in the first position*/
    public void addFirst(T item) {
        // check
        if (size == items.length) {
            resize(size*RFACTOR);
        }

        // insert item
        items[nextFirst] = item;
        nextFirst -= 1;
        nextFirst = nextFirst%items.length;
        size += 1;
    }

    /** Add an item in the last position*/
    public void addLast(T item) {
        // check
        if (size == items.length) {
            resize(size*RFACTOR);
        }

        // insert item
        items[nextLast] = item;
        nextLast += 1;
        nextLast = nextLast%items.length;
        size += 1;
    }

    /** Return true if the deque empty*/
    public boolean isEmpty() {
        if(size==0) {return true;}
        return false;
    }

    /** Return the number of items*/
    public int size() {
        return size;
    }

    /** print all items*/
    public void printDeque() {
        int start = nextFirst + 1;
        for(int i = 0; i<size; i++) {
            System.out.print(items[(start+i)/items.length] + " ");
        }
        System.out.println('\n');
    }

    /** Remove the first item in the deque*/
    public T removeFirst() {
        // check empty
        if(isEmpty()) {
            return null;
        }

        int fullLength = items.length;
        // check usage ratio
        if(size > 16 && (float)size/(float)fullLength < 0.25) {
            resize((int)(fullLength/(float)RFACTOR));
        }

        // remove the first item
        nextFirst += 1;
        nextFirst = nextFirst%fullLength;
        T item = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;

        // return the removed item
        return item;
    }

    /** Remove the last item in the deque*/
    public T removeLast() {
        // check empty
        if(isEmpty()) {
            return null;
        }

        int fullLength = items.length;
        // check usage ratio
        if(size > 16 && (float)size/(float)fullLength < 0.25) {
            resize((int)(fullLength/(float)RFACTOR));
        }

        // remove the last item
        nextLast -= 1;
        nextLast = nextLast%fullLength;
        T item = items[nextLast];
        items[nextLast] = null;
        size -= 1;

        // return the removed item
        return item;
    }

    public T get(int index) {
        if(index > size - 1) {
            System.out.println("Index exceeds bound of box!");
            return null;
        }
        return items[(nextFirst + 1 + index) % items.length];
    }

//    public static void main(String[] args) {
//        ArrayDeque<String> L = new ArrayDeque<>();
//        L.removeFirst();
//        L.addLast("a");
//        L.addLast("b");
//        L.addLast("c");
//        for (int i = 0; i < 100; i++) {
//            L.addLast("d");
//        }
//        L.addLast("d");
//        for (int i = 0; i < 96; i++) {
//            L.removeLast();
//        }
//        L.addLast("e");
//        System.out.println(L.get(8));
//    }

}
