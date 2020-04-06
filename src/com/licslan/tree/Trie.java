package com.licslan.tree;
/**
 * 字典树  trie  多叉树  每个节点有若干指向下个节点的指针
 *
 *
 * 考虑不同语言不同情景
 *
 *
 * class Node{
 *     boolean isWord;  //当前是否是一个单词结尾的
 *     Map<char,Node> next;
 * }
 *
 * treeMap   //红黑树底层
 * */

import java.util.TreeMap;
public class Trie {

    //Trie 节点
    private class Node{
        public boolean isWord;
        //汉语 日语  韩语并不是 Character对象  而是字符对象
        public TreeMap<Character,Node> next;

        public Node(boolean isWord){
            this.isWord=isWord;
            next=new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }
    //root
    private Node root;
    //size
    private int size;


    public Trie(){
        root=new Node();
        size=0;
    }

    //getSize
    public int getSize(){
        return size;
    }

    //add e 往Tire中添加元素单词 建立Tire字典树
    public void add(String word){
        Node cur=root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(cur.next.get(c)==null)cur.next.put(c,new Node());
            cur=cur.next.get(c);
        }
        if(!cur.isWord){
            cur.isWord=true;
            size++;
        }
    }


}
