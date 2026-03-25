package collections.tests.unit;

import collections.interfaces.LRUCache;
import collections.implementations.LRUCacheImpl;
import collections.implementations.Node;

import java.util.List;
import java.util.ArrayList;

public class LRUCacheImplTest {

    private static int          TEST_COUNT = 8;
    private static int          CACHE_DEFAULT_SIZE = 3;

    public static void main(String[] args) {

        System.out.println("Running LRUCacheImplTest:");

        int successCount = 0;
        
        StringBuilder failingTest = new StringBuilder();

        if (put_returnTrue_onEmptyCache_andNodeInstertedToHead()) {
            successCount++;
        } else {
            failingTest.append("\t\t- put_returnTrue_onEmptyCache_andNodeInstertedToHead\n");
        }

        if (put_returnTrue_onFilledCache_andNodeMovedToHead()) {
            successCount++;
        } else {
            failingTest.append("\t\t- put_returnTrue_onFilledCache_andNodeMovedToHead\n");
        }

        if (put_returnTrue_onFullCache_andLeastElemEvicted()) {
            successCount++;
        } else {
            failingTest.append("\t\t- put_returnTrue_onFullCache_andLeastElemEvicted\n");
        }

        if (get_returnNull_onEmptyCache()) {
            successCount++;
        } else {
            failingTest.append("\t\t- get_returnNull_onEmptyCache\n");
        }

        if (get_returnVal_onFilledCache_andNodeMovedToHead()) {
            successCount++;
        } else {
            failingTest.append("\t\t- get_returnVal_onFilledCache_andNodeMovedToHead\n");
        }

        if (remove_returnFalse_onEmptyCache()) {
            successCount++;
        } else {
            failingTest.append("\t\t- remove_returnFalse_onEmptyCache\n");
        }

        if (remove_returnFalse_onFilledCache_withNonExistentKey()) {
            successCount++;
        } else {
            failingTest.append("\t\t- remove_returnFalse_onFilledCache_withNonExistentKey\n");
        }

        if (remove_returnTrue_onFilledCache_andNodeRemoved()) {
            successCount++;
        } else {
            failingTest.append("\t\t- remove_returnTrue_onFilledCache_andNodeRemoved\n");
        }

        System.out.println(String.format("\t- %d/%d Success", successCount, TEST_COUNT));

        if (successCount < TEST_COUNT) {
            System.out.print("\t- Failing Test Cases: \n" + failingTest.toString());
        }

        System.out.println("=============================================");

    }

    private static boolean put_returnTrue_onEmptyCache_andNodeInstertedToHead() {
        
        LRUCache cache = new LRUCacheImpl(CACHE_DEFAULT_SIZE);

        boolean isPutSuccess = cache.put(0, "zero");
        boolean isNodeOnHead = cache.getHead() != null ? "zero".equals(cache.getHead().getKey()) :  false;

        return isPutSuccess && isNodeOnHead;

    }

    private static boolean put_returnTrue_onFilledCache_andNodeMovedToHead() {
        
        LRUCache cache = new LRUCacheImpl(CACHE_DEFAULT_SIZE);

        cache.put(0, "zero");

        boolean isPutSuccess = cache.put(1, "one");
        boolean isNodeOnHead = cache.getHead() != null ? "one".equals(cache.getHead().getKey()) :  false;

        return isPutSuccess && isNodeOnHead;

    }

    private static boolean put_returnTrue_onFullCache_andLeastElemEvicted() {
        
        LRUCache cache = new LRUCacheImpl(CACHE_DEFAULT_SIZE);

        cache.put(0, "zero");
        cache.put(1, "one");
        cache.put(2, "two");

        boolean isPutSuccess = cache.put(3, "three");
        boolean isLeastElemEvicted = cache.get(0) == null;

        return isPutSuccess && isLeastElemEvicted;

    }

    private static boolean get_returnNull_onEmptyCache() {
        
        LRUCache cache = new LRUCacheImpl(CACHE_DEFAULT_SIZE);

        boolean isElemNull = cache.get(0) == null;

        return isElemNull;

    }
    
    private static boolean get_returnVal_onFilledCache_andNodeMovedToHead() {
        
        LRUCache cache = new LRUCacheImpl(CACHE_DEFAULT_SIZE);

        cache.put(0, "zero");
        cache.put(1, "one");

        boolean isElemEqual = "one".equals(cache.get(1));
        boolean isNodeOnHead = cache.getHead() != null ? "one".equals(cache.getHead().getKey()) :  false;

        return isElemEqual && isNodeOnHead;

    }

    private static boolean remove_returnFalse_onEmptyCache() {
        
        LRUCache cache = new LRUCacheImpl(CACHE_DEFAULT_SIZE);

        boolean isRemoveFailed = cache.remove(0) == false;

        return isRemoveFailed;

    }

    private static boolean remove_returnFalse_onFilledCache_withNonExistentKey() {
        
        LRUCache cache = new LRUCacheImpl(CACHE_DEFAULT_SIZE);

        cache.put(0, "zero");
        cache.put(1, "one");

        boolean isRemoveFailed = cache.remove(2) == false;

        return isRemoveFailed;

    }

    private static boolean remove_returnTrue_onFilledCache_andNodeRemoved() {
        
        LRUCache cache = new LRUCacheImpl(CACHE_DEFAULT_SIZE);

        cache.put(0, "zero");
        cache.put(1, "one");
        cache.put(2, "two");

        boolean isRemoveSuccess = cache.remove(1) == true;

        Node head = cache.getHead();
        Node curr = head;

        boolean isNodeFound = false;

        while (curr != null) {

            if (curr.getVal() == "one")  {
                isNodeFound = true;
            }

            curr = curr.getNext();
        }

        return isRemoveSuccess && !isNodeFound;

    }
    
}