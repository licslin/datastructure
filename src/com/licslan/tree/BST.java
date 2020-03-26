package com.licslan.tree;
/**
 * Binary Search Tree
 * 泛型但要是可比较的类型
 * */
public class BST<E extends Comparable<E>>{

    //修饰为私有
    private class Node{
        public E e;
        public Node left,right;
        public Node(E e){
            this.e=e;
            left=null;
            right=null;
        }
    }
    //根节点
    private Node root;
    //bst中存储了多少个元素
    private int size;
    //构造函数
    public BST(){
        root=null;
        size=0;
    }
    //成员方法
    public int size(){
        return size;
    }
    //成员方法
    public boolean isEmpty(){
        return size==0;
    }


}
