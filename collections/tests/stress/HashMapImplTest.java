package collections.tests.stress;

import collections.interfaces.HashMap;
import collections.implementations.HashMapImpl;

public class HashMapImplTest {

    public static void main(String[] args) {

        int[] samples = new int[]{10, 100, 1000, 10000, 100000};

        for (int sample: samples) execute(sample);

    }

    private static void execute(int sampleSize) {

        HashMapImpl<String> hashMap = new HashMapImpl<>();

        long start = System.nanoTime();
        for (int i = 0; i < sampleSize; i++) {
            hashMap.put(i, "Value" + i);
        }
        long end = System.nanoTime();

        System.out.println(
            String.format("Put %d item in %d ns", sampleSize, end - start)
        );

        System.out.println("- Bucket Count: " + hashMap.getBucketCount());
        System.out.println("- Filled Bucket Count: " + hashMap.getFilledBucketCount());
        System.out.println("- Empty Bucket Count: " + hashMap.getEmptyBucketCount());
        System.out.println("- Max Collision: " + hashMap.getMaxCollision());

        start = System.nanoTime();
        for (int i = 0; i < sampleSize; i++) {
            hashMap.get(i);
        }
        end = System.nanoTime();
        
        System.out.println(
            String.format("Get %d item in %d ns", sampleSize, end - start)
        );

        start = System.nanoTime();
        for (int i = 0; i < sampleSize; i++) {
            hashMap.remove(i);
        }
        end = System.nanoTime();
        
        System.out.println(
            String.format("Remove %d item in %d ns", sampleSize, end - start)
        );

        System.out.println("============");
    }

}