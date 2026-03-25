package collections.implementations;

import collections.interfaces.LRUCache;
import collections.implementations.Node;

public class LRUCacheImpl implements LRUCache {

    private int         size;

    public LRUCacheImpl(int size) {
        this.size = size;
    }

    @Override
    public boolean put(int key, String val) {
        return false;
    }

    @Override
    public String get(int key) {
        return null;
    }

    @Override
    public boolean remove(int key) {
        return false;
    }

    @Override
    public Node getHead() {
        return null;
    }

}