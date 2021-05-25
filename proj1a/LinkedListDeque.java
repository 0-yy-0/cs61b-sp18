import java.sql.PseudoColumnUsage;
import java.util.TreeMap;

public class  LinkedListDeque<T> {
    public class Node{
        private Node prev;
        private T item;
        private Node next;

        public Node(LinkedListDeque<T>.Node prev, T item, LinkedListDeque<T>.Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }
    private Node sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel  = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item){
        Node newNode = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    public void addLast(T item){
        Node newnode = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = newnode;
        sentinel.prev = newnode;
        size += 1;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque() {
        for (Node current = sentinel.next; current != sentinel; current = current.next) {
            if (current.next == sentinel) {
                System.out.println(current.item);
                break;
            }
            System.out.print(current.item + " ");
        }
    }

    public T removeFirst(){
        if (isEmpty()) {
            return null;
        }
        T res = sentinel.next.item;
        sentinel.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return res;
    }

    public T removeLast(){
        if (isEmpty()) {
            return null;
        }
        T res = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return res;
    }

    public T get(int index){
        if (size < index) {
            return null;
        }
        Node current = sentinel.next;
        while (index > 0) {
            current = current.next;
            index -= 1;
        }
        return current.item;
    }

    public T getRecursive(int index){
        if (size < index) {
            return null;
        }

        return getRecursive(sentinel.next, index);
    }
    private T getRecursive(LinkedListDeque<T>.Node node, int index){
        if (index == 0){
            return node.item;
        }
        return getRecursive(node.next, index - 1);
    }
}
