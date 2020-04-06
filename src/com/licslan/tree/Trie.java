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

import com.licslan.setmap.BSTSet;
import com.licslan.setmap.FileOperation;

import java.util.ArrayList;
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


    //查询单词word是否包含在tire中
    public boolean contains(String word){
        Node cur=root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(cur.next.get(c)==null)return false;
            cur=cur.next.get(c);
        }
        return cur.isWord;
    }



    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)){

            long startTime = System.nanoTime();

            BSTSet<String> set = new BSTSet<>();
            for(String word: words)
                set.add(word);

            for(String word: words)
                set.contains(word);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + set.getSize());
            System.out.println("BSTSet: " + time + " s");

            // ---

            startTime = System.nanoTime();

            Trie trie = new Trie();
            for(String word: words)
                trie.add(word);

            for(String word: words)
                trie.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie.getSize());
            System.out.println("Trie: " + time + " s");
        }
    }

}
