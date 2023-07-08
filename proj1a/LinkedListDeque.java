public class LinkedListDeque<T> {
    /** inner class Node. */
    public class Node {
        /** the item stored on this node. */
        private T item;
        /** the Node before this Node. **/
        private Node prev;
        /** the Node after this Node. **/
        private Node next;

        /** constructor for Node. */
        public Node(T x, Node p, Node q) {
            item = x;
            prev = p;
            next = q;
        }

        /** constructor for Node.(especially for sentinel node). */
        public Node(Node p, Node q) {
            prev = p;
            next = q;
        }
    }

    private Node sentinel;
    private int size;

    /** constructor for deque. */
    public LinkedListDeque() {
        sentinel = new Node(null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

//    public LinkedListDeque(T x) {
//        sentinel = new Node(null, null);
//        Node newNode = new Node(x, sentinel, sentinel);
//        sentinel.next = newNode;
//        sentinel.prev = newNode;
//        size = 1;
//    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    // Adds an item to the front of the list.
    public void addFirst(T item) {
        Node newNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    // Adds an item to the end of the list.
    public void addLast(T item) {
        Node newNode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    // Prints the items in the deque from first to last, separated by a space.
    public void printDeque() {
        Node pointer = sentinel.next;
        while (pointer != sentinel) {
            System.out.print(pointer.item + " ");
            pointer = pointer.next;
        }
    }

    /* Removes and returns the item at the front of the deque.
    /* If no such item exists, returns null. */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T head = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;

        return  head;
    }

    /* Removes and returns the item at the back of the deque.
    /* If no such item exists, returns null. */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T tail = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;

        return tail;
    }

    /* Gets the item at the given index.
    /* If no such item exists, returns null.
    /* Must not alter the deque! */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node pointer = sentinel.next;
        for (int i = 0; i != index; i++) {
            pointer = pointer.next;
        }

        return pointer.item;
    }

    // Same as get, but uses recursion.
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node pointer, int index) {
        if (index == 0) {
            return pointer.item;
        }

        return getRecursiveHelper(pointer.next, index - 1);
    }
}