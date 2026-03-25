package collections.tests.unit;

import collections.interfaces.LRUCache;
import collections.implementations.LRUCacheImpl;

public class LRUCacheImplTest {

    private int         TEST_COUNT = 8;

    public static void main(String[] args) {

        

    }

    private static boolean put_returnTrue_onEmptyCache_andNodeInstertedToHead() {
        return false;
    }

    private static boolean put_returnTrue_onFilledCache_andNodeMovedToHead() {
        return false;
    }

    private static boolean put_returnTrue_onFullCache_andLeastElemEvicted() {
        return false;
    }

    private static boolean get_returnNull_onEmptyCache() {
        return false;
    }
    
    private static boolean get_returnVal_onFilledCache_andNodeMovedToHead() {
        return false;
    }

    private static boolean remove_returnFalse_onEmptyCache() {
        return false;
    }

    private static boolean remove_returnFalse_onFilledCache_withNonExistentKey() {
        return false;
    }

    private static boolean remove_returnTrue_onFilledCache_andNodeRemoved() {
        return false;
    }
    
}