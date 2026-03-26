package collections.interfaces;

public interface HashMap<T> {

    void put(int key, T val);

    T get(int key);

    void remove(int key);

    int size();

}