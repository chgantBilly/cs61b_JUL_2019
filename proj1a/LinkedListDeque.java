import java.util.List;

public class LinkedListDeque<T> {

    private ListNode sentinel;
    private int size;

    public class ListNode {
        public ListNode prev;
        public T item;
        public ListNode next;

        public ListNode(T item, ListNode prev, ListNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    public LinkedListDeque() {
        size = 0;
        sentinel = new ListNode((T) null,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public LinkedListDeque(LinkedListDeque other) {
        this();
        for (int i = 0; i < other.size(); i++) {
            addFirst((T) other.get(i));
        }
    }

    public void addFirst(T item) {
        size++;
        ListNode node = new ListNode(item, sentinel, sentinel.next);
        sentinel.next.prev = node;
        sentinel.next = node;
    }

    public void addLast(T item) {
        size++;
        ListNode node = new ListNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = node;
        sentinel.prev = node;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }

    public void printDeque() {
        ListNode ptr = sentinel.next;
        while (ptr != sentinel) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size--;
        ListNode nodeToRemove = sentinel.next;
        ListNode next = nodeToRemove.next;
        T item = nodeToRemove.item;
        sentinel.next = next;
        next.prev = sentinel;
        return item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        ListNode nodeToRemove = sentinel.prev;
        ListNode prev = nodeToRemove.prev;
        T item = nodeToRemove.item;
        sentinel.prev = prev;
        prev.next = sentinel;
        return item;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }

        ListNode ptr= sentinel.next;
        while (index > 0) {
            ptr = ptr.next;
            index--;
        }
        return ptr.item;
    }

    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }

        ListNode ptr= sentinel.next;
        return getRecursiveHelper(index, ptr);

    }

    private T getRecursiveHelper(int index, ListNode ptr) {
        if (index == 0) {
            return ptr.item;
        }
        return getRecursiveHelper(index - 1, ptr.next);
    }

}
