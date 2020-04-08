package com.licslan.hashcode;

import java.util.TreeMap;

/**
 * 实现一个hashtable  此时底层使用的TreeMap  没有像Java8那样先使用链表 后面当冲突较为严重后就会转换为红黑树了
 * 此时可以支持动态扩缩容了
 *
 * 回忆一下动态数组的均摊复杂度  平均复杂度O(1)
 *
 * 对于hash表来说 元素从N增加到upperTol*N  地址空间增倍  平均复杂度O(1)  每个操作在O(lowerTol)~O(upperTol)  -->均摊复杂度O(1) 缩容同理
 *
 * 哈希表  均摊复杂度为O(1)
 * 提高性能  那牺牲了什么呢？  顺序性
 *
 * 集合  映射 两种高级的数据结构
 * 可以分为有序集合 有序映射  ---> 底层使用平衡树结构  保证了有序性（TreeMap TreeSet）最大值  最小值  rank  select  前驱  后继操作
 * 可以分为无序集合 无序映射  ---> 底层使用哈希表结构  体现了无序性（HashMap HashSet）
 *
 * 我们实现的hashtable 的bug
 * public class HashTable<K,V>{}      此时的K不要求Comparable
 * private TreeMap<K,V>[] hashtable   此时的K会要求Comparable
 * 二者出现矛盾了！
 * 可以参考JDK8里面关于hashMap底层源码是怎么实现  初始时是链表(不要求K可比较大小)  后期当冲突达到一定程度就会转换为红黑树(要求K可比较大小)结构
 * */
public class HashTableResize01<K, V> {

    //将HashTableResize00优化为全是素数了
    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};

    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private int capacityIndex = 0;

    private TreeMap<K, V>[] hashtable;
    private int size;
    private int M;

    public HashTableResize01(){
        this.M = capacity[capacityIndex];
        size = 0;
        hashtable = new TreeMap[M];
        for(int i = 0 ; i < M ; i ++)
            hashtable[i] = new TreeMap<>();
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

            //记得边界判读  避免数组越界
            if(size >= upperTol * M && capacityIndex + 1 < capacity.length){
                capacityIndex ++;
                resize(capacity[capacityIndex]);
            }
        }
    }

    public V remove(K key){
        V ret = null;
        TreeMap<K, V> map = hashtable[hash(key)];
        if(map.containsKey(key)){
            ret = map.remove(key);
            size --;

            if(size < lowerTol * M && capacityIndex - 1 >= 0){
                capacityIndex --;
                resize(capacity[capacityIndex]);
            }
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

    private void resize(int newM){
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for(int i = 0 ; i < newM ; i ++)
            newHashTable[i] = new TreeMap<>();

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
