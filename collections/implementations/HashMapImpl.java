package collections.implementations;

import collections.interfaces.HashMap;
import collections.implementations.Node;

public class HashMapImpl<T> implements HashMap<T> {

    private int         bucket_size = 16;
    private double      LOAD_TRESHOLD = 0.75;

    private Node<Integer, T>[]      buckets;

    public HashMapImpl() {
        this.buckets = new Node[bucket_size];
    }

    @Override
    public void put(int key, T val) {

        if ((double) size()/buckets.length >= LOAD_TRESHOLD) {
            resize();
        }
        
        int idx = hash(key);
        Node<Integer, T> node = this.buckets[idx];

        if (node == null) {
            this.buckets[idx] = new Node<>(key, val);
            return;
        }

        Node<Integer, T> curr = node;

        while (curr.next != null) {
            curr = curr.next;
        }

        Node<Integer, T> next = new Node<>(key, val);

        next.prev = curr;
        curr.next = next;

        return;
    }

    @Override
    public T get(int key) {
        
        Node<Integer, T> node = getNode(key);

        if (node == null) return null;

        return node.getVal();

    }

    @Override
    public void remove(int key) {
        
        int idx = hash(key);
        Node<Integer, T> node = getNode(key);

        if (node == null) return;

        if (node.prev == null) {

            Node<Integer, T> next = node.next;

            if (next != null) {
                next.prev = null;
            }

            node.next = null;

            this.buckets[idx] = next;

        } else if (node.next == null) {

            Node<Integer, T> prev = node.prev;

            prev.next = null;
            node.prev = null;

        } else {

            Node<Integer, T> prev = node.prev;
            Node<Integer, T> next = node.next;

            prev.next = next;
            next.prev = prev;
            node.prev = null;
            node.next = null;

        }

    }

    @Override
    public int size() {

        int size = 0;

        for (Node<Integer, T> node: this.buckets) {

            if (node == null) continue;

            Node<Integer, T> curr = node;

            while (curr != null) {

                size++;
                curr = curr.next;

            }

        }

        return size;

    }

    public int getBucketCount() {

        return this.buckets.length;

    }


    public int getFilledBucketCount() {

        int bucketCount = 0;
        
        for (Node<Integer, T> node: this.buckets) {
            if (node != null) bucketCount++;
        }
        
        return bucketCount;
    }

    public int getEmptyBucketCount() {

        int emptyBucketCount = 0;
        
        for (Node<Integer, T> node: this.buckets) {
            if (node == null) emptyBucketCount++;
        }
        
        return emptyBucketCount;
    }

    public int getMaxCollision() {
        
        int maxBucketSize = 0;

        for (Node<Integer, T> node: this.buckets) {

            int count = 0;

            if (node != null) {

                Node<Integer, T> curr = node;

                while (curr != null) {
                    count++;
                    curr = curr.next;
                }

            }

            if (count > maxBucketSize) maxBucketSize = count;
        }
        
        return maxBucketSize;
    }

    private int hash(int key) {
        return key % bucket_size;
    }

    private Node<Integer, T> getNode(int key) {

        int idx = hash(key);
        Node<Integer, T> node = this.buckets[idx];

        if (node == null) return null;

        if (node.getKey() == key) {
            return node;
        }

        Node<Integer, T> curr = node.next;

        while (curr != null) {

            if (curr.getKey() == key) {
                return curr;
            }

            curr = curr.next;

        }

        return null;

    }

    private void resize() {

        bucket_size *= 2;

        Node<Integer, T>[] newBuckets = new Node[bucket_size];

        for (Node<Integer, T> node: this.buckets) {

            if (node == null) continue;

            Node<Integer, T> curr = node;

            while (curr != null) {

                curr.prev = null;
                curr.next = null;

                int idx = hash(curr.getKey());

                Node<Integer, T> newNode = newBuckets[idx];

                if (newNode == null) {

                    newBuckets[idx] = curr;

                    curr = curr.next;
                    continue;
                }

                Node<Integer, T> newCurr = newNode;

                while (newCurr.next != null) {
                    newCurr = newCurr.next;
                }

                Node<Integer, T> newNext = curr;

                newNext.prev = newCurr;
                newCurr.next = newNext;

                curr = curr.next;
            }

        }

        this.buckets = newBuckets;

    }

}