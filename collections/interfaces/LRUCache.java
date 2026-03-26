package collections.interfaces;

import collections.implementations.Node;

public interface LRUCache<V> {

    boolean put(int key, V val);

    String get(int key);

    boolean remove(int key);

    // Only for testing
    Node<Integer, V> getHead();

}