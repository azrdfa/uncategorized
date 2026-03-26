package collections.interfaces;

import collections.implementations.Node;

public interface LRUCache<V> {

    Boolean put(int key, V val);

    V get(int key);

    Boolean remove(int key);

    // Only for testing
    Node<Integer, V> getHead();
    Node<Integer, V> getTail();
    Boolean find(int key);

}