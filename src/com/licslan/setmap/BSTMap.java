package com.licslan.setmap;

import java.util.ArrayList;

/**
 * 利用二分搜素树实现映射 use BST implements Map  (CRUD)
 * key need to be comparable
 * */
public class BSTMap<K extends Comparable<K>,V>implements Map<K,V>{

    //node 节点类
    private class Node{
        public K key;
        public V value;
        public Node left,right;
        public Node(K key,V value){
            this.key=key;
            this.value=value;
            left=null;
            right=null;
        }
    }
    //root
    private Node root;
    //map元素个数大小
    private int size;
    //构造函数初始化
    public BSTMap(){
        root=null;
        size=0;
    }

    @Override
    public int getSize(){
        return size;
    }
    @Override
    public boolean isEmpty(){
        return size==0;
    }
    @Override
    public void add(K key,V value){
        root=add(root,key,value);
    }


    //以node节点为根的二分搜索树中添加元素（key,value）递归算法
    //添加元素后返回二分搜索树的根
    private Node add(Node node, K key, V value) {

        //如果递归到底
        if(node==null){size++;return new Node(key, value);}
        //如果没有递归到底
        if(key.compareTo(node.key)<0){
            node.left=add(node.left,key,value);
        }else if(key.compareTo(node.key)>0){
            node.right=add(node.right,key,value);
        }else //key.compareTo(node.key)=0  如果相等  就重新将key赋值
            node.value=value;
        return node;
    }
    //返回以node为根节点的二分搜索树中，key所在的节点  递归算法
    private Node getNode(Node node,K key){
        if(node==null)return null;
        //找到了
        if(key.compareTo(node.key)==0){
            return node;
        }
        //没有找到 继续像左子树方向找
        else if(key.compareTo(node.key)<0){
            return getNode(node.left,key);
        }
        //没有找到 继续像右子树方向找
        else return getNode(node.right,key);
    }
    @Override
    public boolean contains(K key){
        return getNode(root,key)!=null;
    }
    @Override
    public V get(K key){
        Node cur=getNode(root,key);
        return cur==null?null:cur.value;
    }
    @Override
    public void set(K key,V newValue){
        Node node = getNode(root, key);
        if(node==null)throw new IllegalArgumentException(key+"  doesn`t exist!");
        node.value=newValue;
    }

    private Node minimum(Node node) {
        //最小值肯定是最左边的 如果node左子树为空  那么node节点最小
        if(node.left==null)return node;
        //否则递归返回去找左子树的结果
        return minimum(node.left);
    }

    //删除以node为根的二分搜索树中的最小节点  返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        if(node.left==null){
            //如果要删除的节点的左子树没有  那么本节点最小  是要被删除的啊
            //先把把要删除的节点的右子树先存在Node nodeRight中
            //本节点最小删除后  那么本节点的右子树将作为删除后的二分搜索树的根返回就行
            Node nodeRight= node.right;
            //之前的右子树置为null  成为新主 根节点了返回即可
            node.right=null;
            size--;
            //返回新的根  以前的本节点<最小的(没有左子树)>被干掉  换成了如今的右子树了
            return nodeRight;
        }
        //如果左子树不为空  那么该节点左子树就要被删除的
        node.left=removeMin(node.left);
        //再返回当前的node  只不过当前node的左子树已经没有了 右子树还在
        return node;
    }


    //从二分搜索树中删除任意一个指定的元素
    @Override
    public V remove(K key){
        Node node=getNode(root,key);
        if(node!=null){
            root=remove(root,key);
            return node.value;
        }
        return null;
    }

    //删除以node为根的二分搜索树中键为key的节点  递归算法  返回删除节点后新的二分搜索树的根
    private Node remove(Node node, K key) {
        if( node == null )
            return null;

        if(key.compareTo(node.key) < 0 ){
            node.left = remove(node.left , key);
            return node;
        }
        else if(key.compareTo(node.key) > 0 ){
            node.right = remove(node.right, key);
            return node;
        }
        else{   // e.compareTo(node.e) == 0

            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况
            // 这个时候找的是后继 也可以找前驱试试 将2个树融合成一颗树

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    public static void main(String[] args) {
        System.out.println("xxxxx");
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("src/com/licslan/setmap/test.txt",words)){
            System.out.println("Total"+words.size());
            //基于二分搜索树的映射BSTMap大大快于LinkedListMap
            BSTMap<String,Integer> map = new BSTMap<>();
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
