package com.licslan.setmap;
/**
 * 映射  dic 键-->值  key-->value
 * */
public interface Map<K,V> {
    void add(K key,V value);
    V remove(K key);
    boolean contains(K key);
    V get(K key);
    void  set(K key,V newvalue);
    int getSize();
    boolean isEmpty();
}
