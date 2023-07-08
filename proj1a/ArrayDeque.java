public class ArrayDeque<T> {

    /**
     * array to save data.
     */
    private T[] items;
    /**
     * size of the deque.
     */
    private int size;

    private int front;

    private int last;

    /**
     * constructor for ArrayDeque.
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        front = 0;
        last = 1;
    }

    /**
     * Whether to downsize the deque.
     */
    private boolean isSparse() {
        return items.length >= 16 && size < (items.length / 4);
    }

    /**
     * Resize the deque.
     */
    private void resize(int capacity) {
        T[] newDeque = (T[]) new Object[capacity];
        int oldIndex = plusOne(front); // the index of the first item in original deque
        for (int newIndex = 0; newIndex < size; newIndex++) {
            newDeque[newIndex] = items[oldIndex];
            oldIndex = plusOne(oldIndex);
        }
        items = newDeque;
        front = capacity - 1; // since the new deque is starting from true 0 index.
        last = size;

    }

    /**
     * Upsize the deque.
     */
    private void upSize() {
        resize(size * 2);
    }

    /**
     * Downsize the deque
     */
    private void downSize() {
        resize(items.length / 2);
    }

    /**
     * decide if the deque is empty.
     *
     * @return true if the deque is empty, vice versa.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    // Check if the deque is full.
    private boolean isFull() {
        return size == items.length;
    }

    /**
     * return the size of the deque.
     */
    public int size() {
        return size;
    }

    /**
     * return the "index - 1".
     *
     * @param index index
     */
    private int minusOne(int index) {
        // unlike Python, in Java, the % symbol represents "remainder" rather than "modulus",
        // therefore, it may give negative value, so + items.length is necessary,
        // or to use Math.floorMod(x, y)
        return (index - 1 + items.length) % items.length;
    }

    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    /** add one item at the front of the deque.
     * @param item the item we want to add
     */
    public void addFirst(T item) {
        if (isFull()) {
            upSize();
        }
        items[front] = item;
        front = minusOne(front);
        size++;
    }

    /** add one item at the end of the deque.
     * @param item item we want to add
     */
    public void addLast(T item) {
        if (isFull()) {
            upSize();
        }
        items[last] = item;
        last = plusOne(last);
        size++;
    }

    // Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst() {
        if (isSparse()) {
            downSize();
        }

        if (isEmpty()) {
            return null;
        }
        front = plusOne(front);
        T head = items[front];
        items[front] = null;
        size--;

        return head;
    }

    // Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast() {
        if (isSparse()) {
            downSize();
        }

        if (isEmpty()) {
            return null;
        }
        last = minusOne(last);
        T tail = items[last];
        items[last] = null;
        size--;

        return tail;
    }

    /** Gets the item at the given index,
     * where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int start = plusOne(front);

        return items[(start + index) % items.length];
    }

    // Prints the items in the deque from first to last, separated by a space.
    public void printDeque() {
        int pointer = plusOne(front);
        while (pointer != last) {
            System.out.print(items[pointer] + " ");
        }
        System.out.println();
    }
}