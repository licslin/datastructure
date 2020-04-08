package com.licslan.hashcode;

import java.util.TreeMap;

/**
 * 实现一个hashtable  此时底层使用的TreeMap  没有像Java8那样先使用链表 后面当冲突较为严重后就会转换为红黑树了
 * 此时可以支持动态扩缩容了
 *
 * 回忆一下动态数组的均摊复杂度  平均复杂度O(1)
 *
 * 对于hash表来说 元素从N增加到upperTol*N  地址空间增倍  平均复杂度O(1)  每个操作在O(lowerTol)~O(upperTol)  -->均摊复杂度O(1) 缩容同理
 * */
public class HashTableResize00<K, V> {

    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private static final int initCapacity = 7;

    //底层存储结构是TreeMap  (红黑树)
    private TreeMap<K, V>[] hashtable;
    private int size;
    //取模使用的素数  此时这个M对性能影响还是比较大的
    private int M;

    public HashTableResize00(int M){
        this.M = M;
        size = 0;
        hashtable = new TreeMap[M];
        for(int i = 0 ; i < M ; i ++)
            hashtable[i] = new TreeMap<>();
    }

    public HashTableResize00(){
        this(initCapacity);
    }

    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize(){
        return size;
    }

    public void add(K key, V value){
        TreeMap<K, V> map = hashtable[hash(key)];
        if(map.containsKey(key))
            map.put(key, value);
        else{
            map.put(key, value);
            size ++;

            if(size >= upperTol * M)
                //扩容 随着数据增加 此时素数变成合数  可能会影响元素分布不均匀
                //解决方案使用的之前用链接  使用不同的素数表
                resize(2 * M);
        }
    }

    public V remove(K key){
        V ret = null;
        TreeMap<K, V> map = hashtable[hash(key)];
        if(map.containsKey(key)){
            ret = map.remove(key);
            size --;

            if(size < lowerTol * M && M / 2 >= initCapacity)
                resize(M / 2);
        }
        return ret;
    }

    public void set(K key, V value){
        TreeMap<K, V> map = hashtable[hash(key)];
        if(!map.containsKey(key))
            throw new IllegalArgumentException(key + " doesn't exist!");

        map.put(key, value);
    }

    public boolean contains(K key){
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key){
        return hashtable[hash(key)].get(key);
    }

    //扩缩容
    private void resize(int newM){
        //申明一个新的hashtable
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for(int i = 0 ; i < newM ; i ++)
            newHashTable[i] = new TreeMap<>();


        //将老的值放入到新的hashtable中
        int oldM = M;
        this.M = newM;
        for(int i = 0 ; i < oldM ; i ++){
            TreeMap<K, V> map = hashtable[i];
            for(K key: map.keySet())
                newHashTable[hash(key)].put(key, map.get(key));
        }

        this.hashtable = newHashTable;
    }
}
