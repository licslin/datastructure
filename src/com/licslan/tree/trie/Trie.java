package com.licslan.tree.trie;
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
import java.util.Stack;
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

    //查询是否在Tire中有单词以prefix为前缀  又称为前缀树
    public boolean isPrefix(String prefix){
        Node cur=root;
        for(int i=0;i<prefix.length();i++){
            char c = prefix.charAt(i);
            if(cur.next.get(c)==null)return false;
            cur=cur.next.get(c);
        }
        return true;
    }

    // 删除word, 返回是否删除成功
    public boolean remove(String word){

        // 将搜索沿路的节点放入栈中
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        for(int i = 0; i < word.length(); i ++){
            if(!stack.peek().next.containsKey(word.charAt(i)))
                return false;
            stack.push(stack.peek().next.get(word.charAt(i)));
        }

        if(!stack.peek().isWord)
            return false;

        // 将该单词结尾isWord置空
        stack.peek().isWord = false;
        size --;

        // 如果单词最后一个字母的节点的next非空，
        // 说明trie中还存储了其他以该单词为前缀的单词，直接返回
        if(stack.peek().next.size() > 0)
            return true;
        else
            stack.pop();

        // 自底向上删除
        for(int i = word.length() - 1; i >= 0; i --){
            stack.peek().next.remove(word.charAt(i));
            // 如果一个节点的isWord为true，或者是其他单词的前缀，则直接返回
            if(stack.peek().isWord || stack.peek().next.size() > 0)
                return true;
            stack.pop();
        }
        return true;
    }

    //LeetCode NO208 实现Tire  前缀树

    //LeetCode NO211 添加搜索单词
    class WordDictionary {

        private class Node{

            public boolean isWord;
            public TreeMap<Character, Node> next;

            public Node(boolean isWord){
                this.isWord = isWord;
                next = new TreeMap<>();
            }

            public Node(){
                this(false);
            }
        }

        private Node root;

        /** Initialize your data structure here. */
        public WordDictionary() {
            root = new Node();
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {

            Node cur = root;
            for(int i = 0 ; i < word.length() ; i ++){
                char c = word.charAt(i);
                if(cur.next.get(c) == null)
                    cur.next.put(c, new Node());
                cur = cur.next.get(c);
            }
            cur.isWord = true;
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {
            return match(root, word, 0);
        }

        private boolean match(Node node, String word, int index){

            if(index == word.length())
                return node.isWord;

            char c = word.charAt(index);

            if(c != '.'){
                if(node.next.get(c) == null)
                    return false;
                return match(node.next.get(c), word, index + 1);
            }
            else{
                for(char nextChar: node.next.keySet())
                    if(match(node.next.get(nextChar), word, index + 1))
                        return true;
                return false;
            }
        }
    }

    //LeetCode NO677 Map sum Pairs
    class MapSum {

        private class Node{

            public int value;
            public TreeMap<Character, Node> next;

            public Node(int value){
                this.value = value;
                next = new TreeMap<>();
            }

            public Node(){
                this(0);
            }
        }

        private Node root;

        /** Initialize your data structure here. */
        public MapSum() {

            root = new Node();
        }

        public void insert(String key, int val) {

            Node cur = root;
            for(int i = 0 ; i < key.length() ; i ++){
                char c = key.charAt(i);
                if(cur.next.get(c) == null)
                    cur.next.put(c, new Node());
                cur = cur.next.get(c);
            }
            cur.value = val;
        }

        public int sum(String prefix) {

            Node cur = root;
            for(int i = 0 ; i < prefix.length() ; i ++){
                char c = prefix.charAt(i);
                if(cur.next.get(c) == null)
                    return 0;
                cur = cur.next.get(c);
            }

            return sum(cur);
        }

        private int sum(Node node){

            int res = node.value;
            for(char c: node.next.keySet())
                res += sum(node.next.get(c));
            return res;
        }
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

    //模式匹配的算法   生物问题  DNA 字符串问题  是一个非常重要的领域  有很多算法

}
