package com.licslan.hashcode;

import java.util.TreeMap;
/**
 * 实现一个hashtable  此时底层使用的TreeMap  没有像Java8那样先使用链表 后面当冲突较为严重后就会转换为红黑树了
 * */
public class HashTable<K, V> {

    //底层存储结构是TreeMap  (红黑树)
    private TreeMap<K, V>[] hashtable;
    private int size;
    //取模使用的素数  此时这个M对性能影响还是比较大的
    private int M;

    public HashTable(int M){
        this.M = M;
        size = 0;
        hashtable = new TreeMap[M];
        for(int i = 0 ; i < M ; i ++)
            hashtable[i] = new TreeMap<>();
    }

    //默认开辟空间97
    public HashTable(){
        this(97);
    }


    //转换成hashtable中对应的索引值  将键转换成索引值  哈希函数
    private int hash(K key){
        //消除key的符号
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize(){
        return size;
    }

    public void add(K key, V value){
        TreeMap<K, V> map = hashtable[hash(key)];
        //判断要加入元素是否存在
        if(map.containsKey(key))
            //存在就修改这个值
            map.put(key, value);
        else{
            //不存在就正真添加这个数据
            map.put(key, value);
            //维护一下size
            size ++;
        }
    }

    public V remove(K key){
        V ret = null;
        TreeMap<K, V> map = hashtable[hash(key)];
        if(map.containsKey(key)){
            ret = map.remove(key);
            size --;
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
}
