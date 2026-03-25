package collections.interfaces;

import collections.implementations.Node;

public interface LRUCache {

    boolean put(int key, String val);

    String get(int key);

    boolean remove(int key);

    // Only for testing
    Node getHead();

}