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

        if (put_returnTrue_onFilledCache_andNodeInsertedToHead()) {
            successCount++;
        } else {
            failingTest.append("\t\t- put_returnTrue_onFilledCache_andNodeInsertedToHead\n");
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
        
        LRUCache<String> cache = new LRUCacheImpl<>(CACHE_DEFAULT_SIZE);

        boolean isPutSuccess = cache.put(0, "zero");
        boolean isNodeOnHead = cache.getHead() != null ? 0 == cache.getHead().getKey() :  false;

        return isPutSuccess && isNodeOnHead;

    }

    private static boolean put_returnTrue_onFilledCache_andNodeInsertedToHead() {
        
        LRUCache<String> cache = new LRUCacheImpl<>(CACHE_DEFAULT_SIZE);

        cache.put(0, "zero");

        boolean isPutSuccess = cache.put(1, "one");
        boolean isNodeOnHead = cache.getHead() != null ? 1 == cache.getHead().getKey() :  false;

        return isPutSuccess && isNodeOnHead;

    }

    private static boolean put_returnTrue_onFullCache_andLeastElemEvicted() {
        
        LRUCache<String> cache = new LRUCacheImpl<>(CACHE_DEFAULT_SIZE);

        cache.put(0, "zero");
        cache.put(1, "one");
        cache.put(2, "two");

        boolean isPutSuccess = cache.put(3, "three");
        boolean isEvictedElemFound = cache.find(0);

        return isPutSuccess && !isEvictedElemFound;

    }

    private static boolean get_returnNull_onEmptyCache() {
        
        LRUCache<String> cache = new LRUCacheImpl<>(CACHE_DEFAULT_SIZE);

        boolean isElemNull = cache.get(0) == null;

        return isElemNull;

    }
    
    private static boolean get_returnVal_onFilledCache_andNodeMovedToHead() {
        
        LRUCache<String> cache = new LRUCacheImpl<>(CACHE_DEFAULT_SIZE);

        cache.put(0, "zero");
        cache.put(1, "one");

        boolean isElemEqual = "one".equals(cache.get(1));
        boolean isNodeOnHead = cache.getHead() != null ? 1 == cache.getHead().getKey() :  false;

        return isElemEqual && isNodeOnHead;

    }

    private static boolean remove_returnFalse_onEmptyCache() {
        
        LRUCache<String> cache = new LRUCacheImpl<>(CACHE_DEFAULT_SIZE);

        Boolean isRemoveSuccess = cache.remove(0);

        if (isRemoveSuccess == null) {
            return false;
        }

        return !isRemoveSuccess;

    }

    private static boolean remove_returnFalse_onFilledCache_withNonExistentKey() {
        
        LRUCache<String> cache = new LRUCacheImpl<>(CACHE_DEFAULT_SIZE);

        cache.put(0, "zero");
        cache.put(1, "one");

        Boolean isRemoveSuccess = cache.remove(2);

        if (isRemoveSuccess == null) {
            return false;
        }

        return !isRemoveSuccess;

    }

    private static boolean remove_returnTrue_onFilledCache_andNodeRemoved() {
        
        LRUCache<String> cache = new LRUCacheImpl<>(CACHE_DEFAULT_SIZE);

        cache.put(0, "zero");
        cache.put(1, "one");
        cache.put(2, "two");

        Boolean isRemoveSuccess = cache.remove(1);

        if (isRemoveSuccess == null) {
            return false;
        }

        Node<Integer, String> head = cache.getHead();
        Node<Integer, String> curr = head;

        boolean isNodeFound = cache.find(1);

        return isRemoveSuccess && !isNodeFound;

    }
    
}