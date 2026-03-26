package collections.implementations;

import collections.interfaces.HashMap;
import collections.interfaces.LRUCache;
import collections.implementations.Node;
import collections.implementations.HashMapImpl;

public class LRUCacheImpl<V> implements LRUCache<V> {

    private int         size;

    private HashMap     hashMap;

    private Node<Integer, V>        head;
    private Node<Integer, V>        tail;

    public LRUCacheImpl(int size) {

        this.size = size;
        this.hashMap = new HashMapImpl();

        this.head = new Node<>();
        this.tail = new Node<>();

        head.setNext(tail);
        tail.setPrev(head);
    }

    @Override
    public Boolean put(int key, V val) {

        if (hashMap.size() < size) {

            Node<Integer, V> node = new Node<>(key, val);
            hashMap.put(key, node);

            Node<Integer, V> next = head.getNext();

            head.setNext(node);
            next.setPrev(node);

            node.setNext(next);
            node.setPrev(head);

        } else {

            Node<Integer, V> node = new Node<>(key, val);
            hashMap.put(key, node);

            Node<Integer, V> anchor = tail.getPrev();
            hashMap.remove(anchor.getKey());

            Node<Integer, V> prev = anchor.getPrev();
            Node<Integer, V> tail = anchor.getNext();

            prev.setNext(node);
            tail.setPrev(node);

            node.setPrev(prev);
            node.setNext(tail);

        }

        return true;

    }

    @Override
    public V get(int key) {
        return null;
    }

    @Override
    public Boolean remove(int key) {
        return null;
    }

    @Override
    public Node<Integer, V> getHead() {
        return this.head.getNext();
    }

    @Override
    public Node<Integer, V> getTail() {
        return this.tail.getPrev();
    }

    @Override
    public Boolean find(int key) {

        Node<Integer, V> curr = this.head.next;

        while (curr != this.tail) {

            if (curr.getKey() == key)  {
                return true;
            }

            curr = curr.getNext();
        }

        return false;

    }

}