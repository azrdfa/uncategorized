package collections.implementations;

public class Node<K, V> {

    K key;
    V val;
    Node prev;
    Node next;

    public Node(K key, V val) {
        this.key = key;
        this.val = val;
    }

    public K getKey() {
        return this.key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getVal() {
        return this.val;
    }

    public void setVal(V val) {
        this.val = val;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getPrev() {
        return this.prev;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return this.next;
    }
}