public class ArrayDeque<T> {

    private T[] items;
    private int left;
    private int right;
    private int capacity = 8;

    public ArrayDeque(){
        items = (T[]) new Object[capacity];
        left = right = 0;
    }

    public void addFirst(T item){
        if (isFull()) {
            resize((int) (capacity / 0.25));
        }
        left = (left - 1 + capacity) % capacity;
        items[left] = item;
    }

    public void addLast(T item){
        if (isFull()) {
            resize((int) (capacity / 0.25));
        }
        left = (left - 1 + capacity) % capacity;
        items[right] = item;
    }

    public boolean isEmpty(){
        return right == left;
    }

    public void printDeque() {
        int size = size();
        for (int i = 0; i < size; i++) {
            System.out.println(items[(left + i) % capacity] + " ");
        }
    }

    public T removeFirst(){
        if (isEmpty()) {
            return null;
        }
        if (isLowUsageRate()) {
            resize((int) (size() * 0.25));
        }
        T res = items[left];
        items[left] = null;
        left = (left + 1 + capacity) % capacity;

        return res;
    }

    public T removeLast(){
        if (isEmpty()) {
            return null;
        }
        if (isLowUsageRate()) {
            resize((int) (size() * 0.25));
        }
        int size = size();
        T res = items[(left + size) % capacity];
        items[(left + size) % capacity] = null;
        right = (right - 1 + capacity) % capacity;
        return res;
    }

    public T get(int index){
        if (size() < index) {
            return null;
        }
        return items[(left + index) % capacity];
    }

    public int size() {
        return (right - left + capacity) % capacity;
    }

    private boolean isFull() {
        return size() == capacity - 1;
    }

    private void resize(int newSize) {
        T[] newArray = (T[]) new Object[newSize];
        int size = size();
        if (left < right) {
            System.arraycopy(items, left, newArray, 0, size);
        } else {
            System.arraycopy(items, left, newArray, 0, capacity - left);
            System.arraycopy(items, 0, newArray, capacity - left + 1, right);
        }
        left = 0;
        right = size;
        items = newArray;
        capacity = newSize;
    }

    private Boolean isLowUsageRate(){
        return size() < (capacity * 0.25);
    }
}
