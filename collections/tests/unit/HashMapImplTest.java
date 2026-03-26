package collections.tests.unit;

import collections.interfaces.HashMap;
import collections.implementations.HashMapImpl;

public class HashMapImplTest {

    public static void main(String[] args) {

        System.out.println(
            String.format("put_get_success: %b", put_get_success())
        );

        System.out.println(
            String.format("put_get_success_on_collision: %b", put_get_success_on_collision())
        );

        System.out.println(
            String.format("remove_success: %b", remove_success())
        );

        System.out.println(
            String.format("remove_success_on_collision: %b", remove_success_on_collision())
        );

        System.out.println(
            String.format("remove_success_on_collision_head: %b", remove_success_on_collision_head())
        );

        System.out.println(
            String.format("remove_success_on_collision_tail: %b", remove_success_on_collision_tail())
        );

        System.out.println(
            String.format("put_success_on_resize: %b", put_success_on_resize())
        );

    }

    private static boolean put_get_success() {

        HashMapImpl<String> hashMap = new HashMapImpl<>();

        hashMap.put(1, "one");

        boolean isSizeRight = hashMap.size() == 1 && hashMap.getMaxCollision() == 1;
        boolean isValueRight = "one".equals(hashMap.get(1));

        return isSizeRight && isValueRight;

    }

    private static boolean put_get_success_on_collision() {

        HashMapImpl<String> hashMap = new HashMapImpl<>();

        hashMap.put(1, "one");
        hashMap.put(17, "seventeen");

        boolean isSizeRight = hashMap.size() == 2 && hashMap.getMaxCollision() == 2;
        boolean isValueRight = "one".equals(hashMap.get(1)) && "seventeen".equals(hashMap.get(17));

        return isSizeRight && isValueRight;

    }

    private static boolean remove_success() {

        HashMapImpl<String> hashMap = new HashMapImpl<>();

        hashMap.put(1, "one");
        hashMap.remove(1);

        boolean isSizeRight = hashMap.size() == 0 && hashMap.getMaxCollision() == 0;
        boolean isValueRight = hashMap.get(1) == null;

        return isSizeRight && isValueRight;

    }

    private static boolean remove_success_on_collision() {

        HashMapImpl<String> hashMap = new HashMapImpl<>();

        hashMap.put(1, "one");
        hashMap.put(17, "seventeen");
        hashMap.put(33, "thirtytree");
        hashMap.remove(17);

        boolean isSizeRight = hashMap.size() == 2 && hashMap.getMaxCollision() == 2;
        boolean isValueRight = "one".equals(hashMap.get(1)) && hashMap.get(17) == null && "thirtytree".equals(hashMap.get(33));

        return isSizeRight && isValueRight;

    }

    private static boolean remove_success_on_collision_head() {

        HashMapImpl<String> hashMap = new HashMapImpl<>();

        hashMap.put(1, "one");
        hashMap.put(17, "seventeen");
        hashMap.put(33, "thirtytree");
        hashMap.remove(1);

        boolean isSizeRight = hashMap.size() == 2 && hashMap.getMaxCollision() == 2;
        boolean isValueRight = hashMap.get(1) == null && "seventeen".equals(hashMap.get(17)) && "thirtytree".equals(hashMap.get(33));

        return isSizeRight && isValueRight;

    }

    private static boolean remove_success_on_collision_tail() {

        HashMapImpl<String> hashMap = new HashMapImpl<>();

        hashMap.put(1, "one");
        hashMap.put(17, "seventeen");
        hashMap.put(33, "thirtytree");
        hashMap.remove(33);

        boolean isSizeRight = hashMap.size() == 2 && hashMap.getMaxCollision() == 2;
        boolean isValueRight = "one".equals(hashMap.get(1)) && "seventeen".equals(hashMap.get(17)) && hashMap.get(33) == null;

        return isSizeRight && isValueRight;

    }

    private static boolean put_success_on_resize() {

        HashMapImpl<String> hashMap = new HashMapImpl<>();

        for (int i = 0; i < 13; i++) {
            hashMap.put(i, String.valueOf(i));
        }

        boolean isSizeRight = hashMap.size() == 13 && hashMap.getMaxCollision() == 1;
        boolean isValueRight = "0".equals(hashMap.get(0)) && "7".equals(hashMap.get(7)) && "12".equals(hashMap.get(12));

        return isSizeRight && isValueRight;

    }

}