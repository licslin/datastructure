package com.licslan.setmap;

import java.util.ArrayList;
/**
 * 利用链表实现映射  use linked implements map (CRUD)
 * */
public class LinkedListMap<K,V>implements Map<K,V> {

    private class Node {
        public K key;
        public V value;
        public Node nxet;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
        }

        public Node(K key) {
            this(key, null, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + " : " + value.toString();
        }
    }

    private Node dummyNode;
    private int size;

    public LinkedListMap() {
        dummyNode = new Node();
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Node getNode(K key) {
        Node cur = dummyNode.nxet;
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur;
            }
            //不断遍历其他节点
            cur = cur.nxet;
        }
        //如果没有找到就返回空
        return null;
    }

    //判断是否包含某个元素
    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node cur = getNode(key);
        /*if(cur!=null){
            return cur.value;
        }else return null;*/
        return cur == null ? null : cur.value;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node != null) {
            dummyNode.nxet = new Node(key, value, dummyNode.nxet);
            size++;
        }
        //可以报错   或者将之前的错误覆盖掉  设计不一样
        else node.value = value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if (node == null) throw new IllegalArgumentException(key + "doesn`t exit");
        node.value = newValue;
    }

    @Override
    public V remove(K key) {
        Node prev = dummyNode;
        while (prev.nxet != null) {
            if (prev.nxet.key.equals(key)) {
                break;
            }
            //不断循环
            prev = prev.nxet;
        }
        if (prev.nxet != null) {
            Node delNode = prev.nxet;
            prev.nxet = delNode.nxet;
            delNode.nxet = null;
            return delNode.value;
        }
        //否则节点中没有我们要删除的元素
        return null;
    }

    public static void main(String[] args) {
        System.out.println("xxxxx");
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("src/com/licslan/setmap/test.txt",words)){
            System.out.println("Total"+words.size());
            LinkedListMap<String,Integer> map = new LinkedListMap<>();
            for(String word:words){
                if(map.contains(word))
                    //包含相同的  就会词频数量加1
                    map.set(word,map.get(word)+1);
                    //不同的话  就新增一个
                else map.add(word,1);
        }
            //一共有多少个单词以及某个单词的词频
            System.out.println("Total different words:"+map.getSize());
            System.out.println("Frequency of nihao"+map.get("nihao"));
        }
    }
}
