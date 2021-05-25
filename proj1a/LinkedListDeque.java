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

    public Node LinkedListDeque(){
        Node sentinel = new Node(null, null, null);
        size = 0;
        return sentinel;
    }
    public void addFirst(T item){
        sentinel.prev = new Node(null, item, this);
        size += 1;
    }

    public void addLast(T item){
        Node current = sentinel;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Node(current, item, null);
        size += 1;
    }

    public boolean isEmpty(){
        if (sentinel.item == null){
            return true;
        }
        else return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node current = sentinel;
        while (current.next != null){
            System.out.print(current.item);
            current = current.next;
        }
        System.out.println();
    }

    public T removeFirst(){
        Node current = sentinel;
        T item = current.item;
        current = current.next;
        current.next = new Node(null, current.next.item, current.next.next);
        size -= 1;
        return item;
    }

    public T removeLast(){
        T current = sentinel.item;
        return current;
    }

    public T get(int index){
        if (sentinel == null) {
            return null;
        }
        Node current = sentinel;
        while (current.next != null) {
            if (index == 0){
                return current.item;
            }
            current = current.next;
            index -= 1;
        }
        return null;
    }

    public T getRecursive(int index){
        private T getRecursive(Node node, int index){
            if (index == 0){
                return node.item;
            }
            return getRecursive(node.next, index-1);
        }
        return getRecursive(sentinel, index);
    }

}
